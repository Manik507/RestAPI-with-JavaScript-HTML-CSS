package com.example.attendance.service;

import com.example.attendance.dto.AttendanceRowResponse;
import com.example.attendance.dto.AttendanceUpsertRequest;
import com.example.attendance.dto.SessionRequest;
import com.example.attendance.dto.SessionResponse;
import com.example.attendance.exception.NotFoundException;
import com.example.attendance.model.*;
import com.example.attendance.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class AttendanceService {

  private final AttendanceSessionRepository sessions;
  private final AttendanceRecordRepository records;
  private final CourseRepository courses;
  private final EnrollmentRepository enrollments;
  private final StudentRepository students;

  public AttendanceService(AttendanceSessionRepository sessions, AttendanceRecordRepository records, CourseRepository courses, EnrollmentRepository enrollments, StudentRepository students) {
    this.sessions = sessions; this.records = records; this.courses = courses; this.enrollments = enrollments; this.students = students;
  }

  @Transactional
  public SessionResponse createOrGet(SessionRequest req) {
    Course course = courses.findById(req.getCourseId()).orElseThrow(() -> new NotFoundException("Course not found: " + req.getCourseId()));
    AttendanceSession sess = sessions.findByCourseIdAndSessionDate(course.getId(), req.getDate())
      .orElseGet(() -> sessions.save(new AttendanceSession(course, req.getDate(), req.getTopic())));
    return toResponse(sess);
  }

  @Transactional(readOnly = true)
  public SessionResponse getSession(Long sessionId) {
    var s = sessions.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found: " + sessionId));
    return toResponse(s);
  }

  @Transactional(readOnly = true)
  public List<SessionResponse> listSessionsForCourse(Long courseId) {
    return sessions.findByCourseIdOrderBySessionDateDesc(courseId).stream().map(AttendanceService::toResponse).toList();
  }

  @Transactional(readOnly = true)
  public List<AttendanceRowResponse> getRoster(Long sessionId) {
    AttendanceSession sess = sessions.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found: " + sessionId));
    // Enrolled students for the course:
    List<Student> courseStudents = enrollments.findByCourseId(sess.getCourse().getId()).stream().map(Enrollment::getStudent).toList();
    // Existing records:
    var existing = records.findBySessionId(sessionId);
    return courseStudents.stream()
      .sorted(Comparator.comparing(Student::getRollNumber, String.CASE_INSENSITIVE_ORDER))
      .map(stu -> {
        var match = existing.stream().filter(r -> r.getStudent().getId().equals(stu.getId())).findFirst().orElse(null);
        var status = match != null ? match.getStatus() : null;
        var name = stu.getFirstName() + " " + stu.getLastName();
        return new AttendanceRowResponse(stu.getId(), stu.getRollNumber(), name, status);
      })
      .toList();
  }

  @Transactional
  public void upsertAttendance(Long sessionId, AttendanceUpsertRequest req) {
    AttendanceSession sess = sessions.findById(sessionId).orElseThrow(() -> new NotFoundException("Session not found: " + sessionId));
    Student student = students.findById(req.getStudentId()).orElseThrow(() -> new NotFoundException("Student not found: " + req.getStudentId()));
    var rec = records.findBySessionIdAndStudentId(sessionId, req.getStudentId())
      .orElseGet(() -> records.save(new AttendanceRecord(sess, student, req.getStatus())));
    rec.setStatus(req.getStatus());
    records.save(rec);
  }

  @Transactional(readOnly = true)
  public Summary summary(Long sessionId) {
    var recs = records.findBySessionId(sessionId);
    long present = recs.stream().filter(r -> r.getStatus() == AttendanceStatus.PRESENT).count();
    long absent = recs.stream().filter(r -> r.getStatus() == AttendanceStatus.ABSENT).count();
    long late = recs.stream().filter(r -> r.getStatus() == AttendanceStatus.LATE).count();
    long excused = recs.stream().filter(r -> r.getStatus() == AttendanceStatus.EXCUSED).count();
    return new Summary(present, absent, late, excused);
  }

  public record Summary(long present, long absent, long late, long excused) {}

  private static SessionResponse toResponse(AttendanceSession s) {
    return new SessionResponse(s.getId(), s.getCourse().getId(), s.getSessionDate(), s.getTopic(), s.getCreatedAt());
  }
}

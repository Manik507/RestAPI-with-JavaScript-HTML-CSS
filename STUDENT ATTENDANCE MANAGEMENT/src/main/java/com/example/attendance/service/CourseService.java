package com.example.attendance.service;

import com.example.attendance.dto.CourseRequest;
import com.example.attendance.dto.CourseResponse;
import com.example.attendance.exception.NotFoundException;
import com.example.attendance.model.Course;
import com.example.attendance.model.Enrollment;
import com.example.attendance.model.Student;
import com.example.attendance.repository.CourseRepository;
import com.example.attendance.repository.EnrollmentRepository;
import com.example.attendance.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
  private final CourseRepository courses;
  private final StudentRepository students;
  private final EnrollmentRepository enrollments;

  public CourseService(CourseRepository courses, StudentRepository students, EnrollmentRepository enrollments) {
    this.courses = courses; this.students = students; this.enrollments = enrollments;
  }

  @Transactional(readOnly = true)
  public List<CourseResponse> list() {
    return courses.findAll().stream().map(CourseService::toResponse).toList();
  }

  @Transactional
  public CourseResponse create(CourseRequest req) {
    var c = new Course(req.getCode(), req.getName(), req.getDescription());
    return toResponse(courses.save(c));
  }

  @Transactional
  public CourseResponse update(Long id, CourseRequest req) {
    var c = courses.findById(id).orElseThrow(() -> new NotFoundException("Course not found: " + id));
    c.setCode(req.getCode());
    c.setName(req.getName());
    c.setDescription(req.getDescription());
    return toResponse(courses.save(c));
  }

  @Transactional
  public void delete(Long id) {
    if (!courses.existsById(id)) throw new NotFoundException("Course not found: " + id);
    courses.deleteById(id);
  }

  @Transactional
  public void enroll(Long courseId, Long studentId) {
    Course course = courses.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found: " + courseId));
    Student student = students.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
    enrollments.findByStudentAndCourse(student, course).orElseGet(() -> enrollments.save(new Enrollment(student, course)));
  }

  @Transactional
  public void unenroll(Long courseId, Long studentId) {
    Course course = courses.findById(courseId).orElseThrow(() -> new NotFoundException("Course not found: " + courseId));
    Student student = students.findById(studentId).orElseThrow(() -> new NotFoundException("Student not found: " + studentId));
    enrollments.findByStudentAndCourse(student, course).ifPresent(enrollments::delete);
  }

  @Transactional(readOnly = true)
  public List<Student> listEnrolled(Long courseId) {
    if (!courses.existsById(courseId)) throw new NotFoundException("Course not found: " + courseId);
    return enrollments.findByCourseId(courseId).stream().map(Enrollment::getStudent).toList();
  }

  private static CourseResponse toResponse(Course c) {
    return new CourseResponse(c.getId(), c.getCode(), c.getName(), c.getDescription());
  }
}

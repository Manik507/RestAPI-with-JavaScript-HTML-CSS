package com.example.attendance.repository;

import com.example.attendance.model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Long> {
  Optional<AttendanceSession> findByCourseIdAndSessionDate(Long courseId, LocalDate sessionDate);
  List<AttendanceSession> findByCourseIdOrderBySessionDateDesc(Long courseId);
}

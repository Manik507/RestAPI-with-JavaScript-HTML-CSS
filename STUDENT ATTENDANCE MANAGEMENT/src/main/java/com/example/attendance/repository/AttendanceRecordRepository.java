package com.example.attendance.repository;

import com.example.attendance.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
  List<AttendanceRecord> findBySessionId(Long sessionId);
  Optional<AttendanceRecord> findBySessionIdAndStudentId(Long sessionId, Long studentId);
}

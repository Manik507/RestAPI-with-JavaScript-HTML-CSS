package com.example.attendance.repository;

import com.example.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
  Optional<Student> findByRollNumberIgnoreCase(String rollNumber);
  List<Student> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String first, String last);
}

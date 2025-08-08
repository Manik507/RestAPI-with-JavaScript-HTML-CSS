package com.example.attendance.repository;

import com.example.attendance.model.Course;
import com.example.attendance.model.Enrollment;
import com.example.attendance.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
  List<Enrollment> findByCourseId(Long courseId);
  List<Enrollment> findByStudentId(Long studentId);
  Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
}

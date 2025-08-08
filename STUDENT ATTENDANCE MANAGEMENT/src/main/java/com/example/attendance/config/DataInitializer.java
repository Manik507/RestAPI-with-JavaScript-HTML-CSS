package com.example.attendance.config;

import com.example.attendance.model.*;
import com.example.attendance.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

  @Bean
  CommandLineRunner seed(
      StudentRepository students,
      CourseRepository courses,
      EnrollmentRepository enrollments
  ) {
    return args -> {
      if (students.count() == 0) {
        var s1 = students.save(new Student("R001", "Alice", "Anderson", "alice@example.com"));
        var s2 = students.save(new Student("R002", "Bob", "Brown", "bob@example.com"));
        var s3 = students.save(new Student("R003", "Carol", "Clark", "carol@example.com"));

        var c1 = courses.save(new Course("CS101", "Intro to CS", "Foundations of computer science"));
        var c2 = courses.save(new Course("MATH201", "Discrete Math", "Logic, sets, combinatorics"));

        enrollments.save(new Enrollment(s1, c1));
        enrollments.save(new Enrollment(s2, c1));
        enrollments.save(new Enrollment(s3, c1));

        enrollments.save(new Enrollment(s1, c2));
        enrollments.save(new Enrollment(s3, c2));
      }
    };
  }
}

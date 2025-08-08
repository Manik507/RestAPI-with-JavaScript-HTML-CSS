package com.example.attendance.controller;

import com.example.attendance.dto.CourseRequest;
import com.example.attendance.dto.CourseResponse;
import com.example.attendance.model.Student;
import com.example.attendance.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

  private final CourseService service;

  public CourseController(CourseService service) { this.service = service; }

  @GetMapping
  public List<CourseResponse> list() {
    return service.list();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CourseResponse create(@Valid @RequestBody CourseRequest req) {
    return service.create(req);
  }

  @PutMapping("/{id}")
  public CourseResponse update(@PathVariable Long id, @Valid @RequestBody CourseRequest req) {
    return service.update(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  @PostMapping("/{courseId}/enroll/{studentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void enroll(@PathVariable Long courseId, @PathVariable Long studentId) {
    service.enroll(courseId, studentId);
  }

  @DeleteMapping("/{courseId}/enroll/{studentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void unenroll(@PathVariable Long courseId, @PathVariable Long studentId) {
    service.unenroll(courseId, studentId);
  }

  @GetMapping("/{courseId}/students")
  public List<Student> enrolled(@PathVariable Long courseId) {
    return service.listEnrolled(courseId);
  }
}

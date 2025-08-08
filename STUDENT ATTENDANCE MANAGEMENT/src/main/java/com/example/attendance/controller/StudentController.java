package com.example.attendance.controller;

import com.example.attendance.dto.StudentRequest;
import com.example.attendance.dto.StudentResponse;
import com.example.attendance.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

  private final StudentService service;

  public StudentController(StudentService service) { this.service = service; }

  @GetMapping
  public List<StudentResponse> list(@RequestParam(required = false) String q) {
    return service.list(q);
  }

  @GetMapping("/{id}")
  public StudentResponse get(@PathVariable Long id) {
    return service.get(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public StudentResponse create(@Valid @RequestBody StudentRequest req) {
    return service.create(req);
  }

  @PutMapping("/{id}")
  public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest req) {
    return service.update(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }
}

package com.example.attendance.service;

import com.example.attendance.dto.StudentRequest;
import com.example.attendance.dto.StudentResponse;
import com.example.attendance.exception.NotFoundException;
import com.example.attendance.model.Student;
import com.example.attendance.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
  private final StudentRepository repo;

  public StudentService(StudentRepository repo) { this.repo = repo; }

  @Transactional(readOnly = true)
  public List<StudentResponse> list(String q) {
    List<Student> result;
    if (q != null && !q.isBlank()) {
      result = repo.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(q, q);
    } else {
      result = repo.findAll();
    }
    return result.stream().map(StudentService::toResponse).toList();
  }

  @Transactional(readOnly = true)
  public StudentResponse get(Long id) {
    var s = repo.findById(id).orElseThrow(() -> new NotFoundException("Student not found: " + id));
    return toResponse(s);
  }

  @Transactional
  public StudentResponse create(StudentRequest req) {
    var s = new Student(req.getRollNumber(), req.getFirstName(), req.getLastName(), req.getEmail());
    return toResponse(repo.save(s));
  }

  @Transactional
  public StudentResponse update(Long id, StudentRequest req) {
    var s = repo.findById(id).orElseThrow(() -> new NotFoundException("Student not found: " + id));
    s.setRollNumber(req.getRollNumber());
    s.setFirstName(req.getFirstName());
    s.setLastName(req.getLastName());
    s.setEmail(req.getEmail());
    return toResponse(repo.save(s));
  }

  @Transactional
  public void delete(Long id) {
    if (!repo.existsById(id)) throw new NotFoundException("Student not found: " + id);
    repo.deleteById(id);
  }

  private static StudentResponse toResponse(Student s) {
    return new StudentResponse(s.getId(), s.getRollNumber(), s.getFirstName(), s.getLastName(), s.getEmail());
  }
}

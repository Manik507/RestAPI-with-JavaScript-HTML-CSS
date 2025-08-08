package com.example.attendance.controller;

import com.example.attendance.dto.AttendanceRowResponse;
import com.example.attendance.dto.AttendanceUpsertRequest;
import com.example.attendance.dto.SessionRequest;
import com.example.attendance.dto.SessionResponse;
import com.example.attendance.service.AttendanceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

  private final AttendanceService service;

  public AttendanceController(AttendanceService service) { this.service = service; }

  @PostMapping("/sessions")
  @ResponseStatus(HttpStatus.CREATED)
  public SessionResponse createOrGet(@Valid @RequestBody SessionRequest req) {
    return service.createOrGet(req);
  }

  @GetMapping("/sessions/{sessionId}")
  public SessionResponse getSession(@PathVariable Long sessionId) {
    return service.getSession(sessionId);
  }

  @GetMapping("/sessions/course/{courseId}")
  public List<SessionResponse> listSessions(@PathVariable Long courseId) {
    return service.listSessionsForCourse(courseId);
  }

  @GetMapping("/sessions/{sessionId}/roster")
  public List<AttendanceRowResponse> roster(@PathVariable Long sessionId) {
    return service.getRoster(sessionId);
  }

  @PostMapping("/sessions/{sessionId}/attendance")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void upsert(@PathVariable Long sessionId, @Valid @RequestBody AttendanceUpsertRequest req) {
    service.upsertAttendance(sessionId, req);
  }

  @GetMapping("/sessions/{sessionId}/summary")
  public AttendanceService.Summary summary(@PathVariable Long sessionId) {
    return service.summary(sessionId);
  }
}

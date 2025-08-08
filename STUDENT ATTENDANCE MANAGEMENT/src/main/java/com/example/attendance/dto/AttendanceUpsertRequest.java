package com.example.attendance.dto;

import com.example.attendance.model.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

public class AttendanceUpsertRequest {
  @NotNull
  private Long studentId;

  @NotNull
  private AttendanceStatus status;

  public Long getStudentId() { return studentId; }
  public void setStudentId(Long studentId) { this.studentId = studentId; }
  public AttendanceStatus getStatus() { return status; }
  public void setStatus(AttendanceStatus status) { this.status = status; }
}

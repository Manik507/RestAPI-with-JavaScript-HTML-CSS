package com.example.attendance.dto;

import com.example.attendance.model.AttendanceStatus;

public class AttendanceRowResponse {
  private Long studentId;
  private String rollNumber;
  private String name;
  private AttendanceStatus status;

  public AttendanceRowResponse() {}

  public AttendanceRowResponse(Long studentId, String rollNumber, String name, AttendanceStatus status) {
    this.studentId = studentId; this.rollNumber = rollNumber; this.name = name; this.status = status;
  }

  public Long getStudentId() { return studentId; }
  public void setStudentId(Long studentId) { this.studentId = studentId; }
  public String getRollNumber() { return rollNumber; }
  public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public AttendanceStatus getStatus() { return status; }
  public void setStatus(AttendanceStatus status) { this.status = status; }
}

package com.example.attendance.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "attendance_records",
  uniqueConstraints = @UniqueConstraint(columnNames = {"session_id", "student_id"}))
public class AttendanceRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "session_id")
  private AttendanceSession session;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "student_id")
  private Student student;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 16)
  private AttendanceStatus status;

  @Column(name = "marked_at", nullable = false)
  private Instant markedAt = Instant.now();

  public AttendanceRecord() {}

  public AttendanceRecord(AttendanceSession session, Student student, AttendanceStatus status) {
    this.session = session;
    this.student = student;
    this.status = status;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public AttendanceSession getSession() { return session; }
  public void setSession(AttendanceSession session) { this.session = session; }
  public Student getStudent() { return student; }
  public void setStudent(Student student) { this.student = student; }
  public AttendanceStatus getStatus() { return status; }
  public void setStatus(AttendanceStatus status) { this.status = status; }
  public Instant getMarkedAt() { return markedAt; }
  public void setMarkedAt(Instant markedAt) { this.markedAt = markedAt; }
}

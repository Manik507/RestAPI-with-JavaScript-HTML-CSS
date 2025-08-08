package com.example.attendance.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "attendance_sessions",
  uniqueConstraints = @UniqueConstraint(columnNames = {"course_id", "session_date"}))
public class AttendanceSession {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "course_id")
  private Course course;

  @Column(name = "session_date", nullable = false)
  private LocalDate sessionDate;

  @Column(length = 255)
  private String topic;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt = Instant.now();

  public AttendanceSession() {}

  public AttendanceSession(Course course, LocalDate date, String topic) {
    this.course = course;
    this.sessionDate = date;
    this.topic = topic;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Course getCourse() { return course; }
  public void setCourse(Course course) { this.course = course; }
  public LocalDate getSessionDate() { return sessionDate; }
  public void setSessionDate(LocalDate sessionDate) { this.sessionDate = sessionDate; }
  public String getTopic() { return topic; }
  public void setTopic(String topic) { this.topic = topic; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

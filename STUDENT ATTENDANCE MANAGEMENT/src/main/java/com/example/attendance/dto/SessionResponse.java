package com.example.attendance.dto;

import java.time.Instant;
import java.time.LocalDate;

public class SessionResponse {
  private Long id;
  private Long courseId;
  private LocalDate date;
  private String topic;
  private Instant createdAt;

  public SessionResponse() {}
  public SessionResponse(Long id, Long courseId, LocalDate date, String topic, Instant createdAt) {
    this.id = id; this.courseId = courseId; this.date = date; this.topic = topic; this.createdAt = createdAt;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public Long getCourseId() { return courseId; }
  public void setCourseId(Long courseId) { this.courseId = courseId; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public String getTopic() { return topic; }
  public void setTopic(String topic) { this.topic = topic; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

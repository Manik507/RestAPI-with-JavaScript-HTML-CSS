package com.example.attendance.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class SessionRequest {
  @NotNull
  private Long courseId;

  @NotNull
  private LocalDate date;

  @Size(max = 255)
  private String topic;

  public Long getCourseId() { return courseId; }
  public void setCourseId(Long courseId) { this.courseId = courseId; }
  public LocalDate getDate() { return date; }
  public void setDate(LocalDate date) { this.date = date; }
  public String getTopic() { return topic; }
  public void setTopic(String topic) { this.topic = topic; }
}

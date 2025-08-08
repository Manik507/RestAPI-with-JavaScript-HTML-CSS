package com.example.attendance.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String code;

  @Column(nullable = false, length = 200)
  private String name;

  @Column(columnDefinition = "text")
  private String description;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt = Instant.now();

  public Course() {}

  public Course(String code, String name, String description) {
    this.code = code;
    this.name = name;
    this.description = description;
  }

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

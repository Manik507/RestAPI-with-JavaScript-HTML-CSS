package com.example.attendance.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "students")
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "roll_number", nullable = false, unique = true, length = 50)
  private String rollNumber;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Column(length = 180)
  private String email;

  @Column(name = "created_at", nullable = false, updatable = false)
  private Instant createdAt = Instant.now();

  public Student() {}

  public Student(String rollNumber, String firstName, String lastName, String email) {
    this.rollNumber = rollNumber;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  // Getters and setters
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }
  public String getRollNumber() { return rollNumber; }
  public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
  public Instant getCreatedAt() { return createdAt; }
  public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}

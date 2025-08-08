package com.example.attendance.dto;

public class StudentResponse {
  private Long id;
  private String rollNumber;
  private String firstName;
  private String lastName;
  private String email;

  public StudentResponse() {}

  public StudentResponse(Long id, String rollNumber, String firstName, String lastName, String email) {
    this.id = id; this.rollNumber = rollNumber; this.firstName = firstName; this.lastName = lastName; this.email = email;
  }

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
}

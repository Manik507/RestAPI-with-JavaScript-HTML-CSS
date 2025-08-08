package com.example.attendance.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class StudentRequest {
  @NotBlank @Size(max = 50)
  private String rollNumber;

  @NotBlank @Size(max = 100)
  private String firstName;

  @NotBlank @Size(max = 100)
  private String lastName;

  @Email @Size(max = 180)
  private String email;

  public String getRollNumber() { return rollNumber; }
  public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
  public String getFirstName() { return firstName; }
  public void setFirstName(String firstName) { this.firstName = firstName; }
  public String getLastName() { return lastName; }
  public void setLastName(String lastName) { this.lastName = lastName; }
  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }
}

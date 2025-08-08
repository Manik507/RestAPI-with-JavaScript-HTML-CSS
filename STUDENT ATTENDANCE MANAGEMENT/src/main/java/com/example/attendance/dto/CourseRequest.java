package com.example.attendance.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourseRequest {
  @NotBlank @Size(max = 50)
  private String code;

  @NotBlank @Size(max = 200)
  private String name;

  @Size(max = 10_000)
  private String description;

  public String getCode() { return code; }
  public void setCode(String code) { this.code = code; }
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }
  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }
}

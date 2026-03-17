package com.learnjiava.employee_management.employee.dto.request;

import com.learnjiava.employee_management.common.validation.ValidInteger;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateEmployeeDTO {
  @NotNull(message = "Name cannot be null")
  @NotEmpty(message = "Name cannot be empty")
  @Size(max = 50, message = "Name can be maximum 50 characters")
  private String name;

  @NotNull(message = "Email cannot be null")
  @NotEmpty(message = "Email cannot be empty")
  @Size(max = 100, message = "Email can be maximum 100 characters")
  @Email(message = "Email should be valid")
  private String email;

  @NotNull(message = "Department ID cannot be null")
  @NotEmpty(message = "Department ID cannot be empty")
  @ValidInteger(message = "Department ID must be a number")
  private String departmentId;

  // parse departmentId to Integer
  public Integer getDepartmentId() {
    try {
      return Integer.parseInt(departmentId);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(e.getMessage());
    }
  }
}

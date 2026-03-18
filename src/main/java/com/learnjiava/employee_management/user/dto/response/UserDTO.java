package com.learnjiava.employee_management.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private String username;
  private String email;
  private String role; // 1: user, 2: admin
}

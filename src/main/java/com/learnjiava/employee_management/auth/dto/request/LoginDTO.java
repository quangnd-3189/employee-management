package com.learnjiava.employee_management.auth.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
  private String username;
  private String password;
}

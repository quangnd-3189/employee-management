package com.learnjiava.employee_management.auth.dto.response;

import java.util.List;
import java.util.UUID;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private UUID id;
  private String username;
  private String email;
  private List<String> roles;

  public UserDTO(UUID id, String username, String email) {
    this.id = id; 
    this.username = username;
    this.email = email;
  }
}

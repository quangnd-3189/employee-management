package com.learnjiava.employee_management.auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learnjiava.employee_management.auth.dto.request.RegisterDTO;
import com.learnjiava.employee_management.auth.dto.response.LoginDataDTO;
import com.learnjiava.employee_management.auth.dto.response.UserDTO;
import com.learnjiava.employee_management.common.jwt.JwtService;
import com.learnjiava.employee_management.user.UserService;
import com.learnjiava.employee_management.user.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final UserService userService;
  private final JwtService jwtService;

  public LoginDataDTO login(String username, String password) {
    User user = userService.getUserByUsername(username);
    if (user == null || !userService.checkPassword(user.getId(), password)) 
      throw new RuntimeException("Invalid username or password");
    
    String token = jwtService.generateToken(new UserDTO(user.getId(), user.getUsername(), user.getEmail(), new ArrayList<>() {
      {
        add(user.getRole());
      }
    }));
    return new LoginDataDTO(token);
  }

  public UserDTO register(RegisterDTO registerDTO) {
    User user = userService.registerUser(registerDTO);
    UserDTO result = new UserDTO();
    result.setId(user.getId());
    result.setUsername(user.getUsername());
    result.setEmail(user.getEmail());
    result.setRoles(Arrays.stream(user.getRole().split(",")).map(String::trim).collect(Collectors.toList()));
    return result;
  }

}

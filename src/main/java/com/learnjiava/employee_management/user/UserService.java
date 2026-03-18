package com.learnjiava.employee_management.user;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.learnjiava.employee_management.auth.dto.request.RegisterDTO;
import com.learnjiava.employee_management.common.UtilityService;
import com.learnjiava.employee_management.user.dto.response.UserDTO;
import com.learnjiava.employee_management.user.entity.User;
import com.learnjiava.employee_management.user.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final UtilityService utilityService;

  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  public UserDTO getUserById(UUID id) {
    User user = userRepository.findById(id)
      .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return new UserDTO(user.getUsername(), user.getEmail(), user.getRole());
  }

  public boolean checkPassword(UUID id, String rawPassword) {
    User user = userRepository.findById(id)
      .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    return passwordEncoder.matches(rawPassword, user.getPassword());
  }

  public User registerUser(RegisterDTO registerDTO) {
    if (userRepository.findByUsername(registerDTO.getUsername()) != null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exists");
    }
    User user = new User();
    user.setId(utilityService.generateUUID());
    user.setUsername(registerDTO.getUsername());
    user.setEmail(registerDTO.getEmail());
    user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
    user.setRole("USER");
    return userRepository.save(user);
  }
}

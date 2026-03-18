package com.learnjiava.employee_management.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjiava.employee_management.auth.dto.request.LoginDTO;
import com.learnjiava.employee_management.auth.dto.request.RegisterDTO;
import com.learnjiava.employee_management.auth.dto.response.LoginDataDTO;
import com.learnjiava.employee_management.auth.dto.response.UserDTO;
import com.learnjiava.employee_management.common.httpresponse.BaseResponse;
import com.learnjiava.employee_management.common.httpresponse.SuccessResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Tag(name = "Authentication", description = "Endpoints for user authentication and authorization")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  
  @PostMapping("/login")
  public BaseResponse<LoginDataDTO> login(@RequestBody LoginDTO entity) {
    LoginDataDTO result = authService.login(entity.getUsername(), entity.getPassword());
    return new SuccessResponse<>(result);
  }

  @PostMapping("/register")
  public BaseResponse<UserDTO> register(@RequestBody RegisterDTO entity) {
    UserDTO result = authService.register(entity);
    return new SuccessResponse<UserDTO>(result);
  }
  
  @GetMapping("/debug")
  public Object debug(Authentication auth) {
    return auth.getAuthorities();
  }
}

package com.learnjiava.employee_management.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjiava.employee_management.common.httpresponse.BaseResponse;
import com.learnjiava.employee_management.common.httpresponse.SuccessResponse;
import com.learnjiava.employee_management.security.CustomUserDetails;
import com.learnjiava.employee_management.user.dto.response.UserDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@EnableMethodSecurity
public class UserController {

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("info")
  public BaseResponse<UserDTO> getUserInfo(Authentication authentication) {
    CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

    String username = user.getUsername();

    Collection<? extends GrantedAuthority> roles = user.getAuthorities();

    UserDTO userDTO = new UserDTO();
    userDTO.setUsername(username);
    userDTO.setRole(roles.stream().map(GrantedAuthority::getAuthority).toList().toString());

    return new SuccessResponse<>(userDTO);
  }
  
}

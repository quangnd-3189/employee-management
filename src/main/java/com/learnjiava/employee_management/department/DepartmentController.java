package com.learnjiava.employee_management.department;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjiava.employee_management.common.httpresponse.BaseResponse;
import com.learnjiava.employee_management.common.httpresponse.SuccessResponse;
import com.learnjiava.employee_management.department.dto.response.DepartmentDTO;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;


@Tag(name = "Department", description = "Department management APIs")
@RestController
@RequestMapping("departments")
@RequiredArgsConstructor
@EnableMethodSecurity
public class DepartmentController {

  private final DepartmentService departmentService;

  @PreAuthorize("hasAnyRole('USER','ADMIN')")
  @GetMapping("/all")
  public BaseResponse<List<DepartmentDTO>> getAllDepartments() {
    List<DepartmentDTO> departments = departmentService.getAllDepartments();
    return new SuccessResponse<List<DepartmentDTO>>(departments);
  }
  
}

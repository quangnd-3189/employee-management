package com.learnjiava.employee_management.employee;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnjiava.employee_management.common.httpresponse.BaseResponse;
import com.learnjiava.employee_management.common.httpresponse.ListResponse;
import com.learnjiava.employee_management.common.httpresponse.SuccessResponse;
import com.learnjiava.employee_management.employee.dto.request.CreateEmployeeDTO;
import com.learnjiava.employee_management.employee.dto.request.UpdateEmployeeDTO;
import com.learnjiava.employee_management.employee.dto.response.EmployeeDTO;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Tag(name = "Employee", description = "Employee management APIs")
@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {
  
  private final EmployeeService employeeService;

  @GetMapping("/")
  public BaseResponse<ListResponse> getListEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    ListResponse employees = employeeService.getListEmployees(page, size);
    return new SuccessResponse<ListResponse>(employees);
  }

  @GetMapping("/all")
  public BaseResponse<List<EmployeeDTO>> getAllEmployees() {
    List<EmployeeDTO> employees = employeeService.getAllEmployees();
    return new SuccessResponse<List<EmployeeDTO>>(employees);
  }

  @GetMapping("/search")
  public BaseResponse<ListResponse> searchEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(required = false) String name, @RequestParam(required = false) String department) {
    ListResponse employees = employeeService.getEmployeeByNameOrDepartment(name, department, page, size);
    return new SuccessResponse<ListResponse>(employees);
  }

  @GetMapping("/search-all")
  public BaseResponse<List<EmployeeDTO>> searchEmployees(@RequestParam(required = false) String name, @RequestParam(required = false) String department) {
    List<EmployeeDTO> employees = employeeService.getAllEmployeeByNameOrDepartment(name, department);
    return new SuccessResponse<List<EmployeeDTO>>(employees);
  }

  @GetMapping("/detail/{id}")
  public BaseResponse<EmployeeDTO> getEmployeeDetail(@PathVariable UUID id) {
    EmployeeDTO employee = employeeService.getEmployeeById(id);
    return new SuccessResponse<EmployeeDTO>(employee);
  }
  

  @PostMapping("/add")
  public BaseResponse<EmployeeDTO> addEmployee(@Valid @RequestBody CreateEmployeeDTO input) {
    EmployeeDTO employee = employeeService.createEmployee(input);
    return new SuccessResponse<EmployeeDTO>(employee);
  }
  
  @PutMapping("/update")
  public BaseResponse<EmployeeDTO> updateEmployee(@Valid @RequestBody UpdateEmployeeDTO input) {
    EmployeeDTO employee = employeeService.updateEmployee(input);
    return new SuccessResponse<EmployeeDTO>(employee);
  }

  @DeleteMapping("/delete/{id}")
  public BaseResponse<UUID> deleteEmployee(@PathVariable UUID id) {
    employeeService.deleteEmployee(id);
    return new SuccessResponse<>(id);
  }
}

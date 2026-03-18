package com.learnjiava.employee_management.employee.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeStatisticDTO {
  private int employeeCount;
  private int departmentCount;
  private List<Object[]> employeePerDepartment;
}

package com.learnjiava.employee_management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnjiava.employee_management.employee.EmployeeService;
import com.learnjiava.employee_management.employee.dto.response.EmployeeStatisticDTO;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class Index {
  
  private final EmployeeService employeeService;

  @GetMapping("/")
  public String index() {
    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/register")
  public String register() {
    return "register";
  }

  @GetMapping("/employees/statistic")
  public String employeeStatistic(Model model) {
    EmployeeStatisticDTO statisticData = employeeService.getEmployeeStatistic();
    model.addAttribute("employeeCount", statisticData.getEmployeeCount());
    model.addAttribute("departmentCount", statisticData.getDepartmentCount());
    model.addAttribute("employeePerDepartment", statisticData.getEmployeePerDepartment());
    return "employee-statistic";
  }
  
}

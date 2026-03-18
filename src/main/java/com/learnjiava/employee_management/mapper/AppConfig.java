package com.learnjiava.employee_management.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.learnjiava.employee_management.department.dto.response.DepartmentDTO;
import com.learnjiava.employee_management.department.entity.Department;
import com.learnjiava.employee_management.employee.dto.response.EmployeeDTO;
import com.learnjiava.employee_management.employee.entity.Employee;

@Configuration
public class AppConfig {
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(12);
  }

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper(){
      @Override
      public <D> D map(Object source, Class<D> destinationType) {
        D destination = super.map(source, destinationType);
        if (source instanceof Employee && destination instanceof EmployeeDTO) {
          Employee employee = (Employee) source;
          EmployeeDTO employeeDTO = (EmployeeDTO) destination;
          employeeDTO.setName(employee.getName());
          employeeDTO.setEmail(employee.getEmail());
          employeeDTO.setDepartmentId(employee.getDepartment() != null ? employee.getDepartment().getId() : null);
          employeeDTO.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null);
        }
        if(source instanceof Department && destination instanceof DepartmentDTO) {
          Department department = (Department) source;
          DepartmentDTO departmentDTO = (DepartmentDTO) destination;
          departmentDTO.setName(department.getName());
        }
        return destination;
      }

    };
  }

}

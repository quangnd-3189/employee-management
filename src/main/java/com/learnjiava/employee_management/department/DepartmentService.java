package com.learnjiava.employee_management.department;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.learnjiava.employee_management.department.dto.response.DepartmentDTO;
import com.learnjiava.employee_management.department.entity.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentService {
  private final DepartmentRepository departmentRepository;
  private final ModelMapper modelMapper;

  public List<DepartmentDTO> getAllDepartments() {
    return departmentRepository.findAll()
        .stream()
        .map(department -> modelMapper.map(department, DepartmentDTO.class))
        .toList();
  }
  
}

package com.learnjiava.employee_management.employee;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.learnjiava.employee_management.cache.CacheService;
import com.learnjiava.employee_management.common.UtilityService;
import com.learnjiava.employee_management.common.httpresponse.ListResponse;
import com.learnjiava.employee_management.department.entity.Department;
import com.learnjiava.employee_management.department.entity.DepartmentRepository;
import com.learnjiava.employee_management.employee.dto.request.CreateEmployeeDTO;
import com.learnjiava.employee_management.employee.dto.request.UpdateEmployeeDTO;
import com.learnjiava.employee_management.employee.dto.response.EmployeeDTO;
import com.learnjiava.employee_management.employee.entity.Employee;
import com.learnjiava.employee_management.employee.entity.EmployeeRepository;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@RequiredArgsConstructor
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final DepartmentRepository departmentRepository;
  private final UtilityService utilityService;
  private final ModelMapper modelMapper;
  private final CacheService cacheService;

  private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

  public int countActiveEmployees() {
    Integer cachedCount = cacheService.getKeyFromCache("employeeCache", "count", Integer.class);
    if (cachedCount != null) {
      logger.info("Cache hit for countActiveEmployees: {}", cachedCount);
      return cachedCount;
    }
    int count = employeeRepository.countByDeletedAtIsNull();
    cacheService.setKeyToCache("employeeCache", "count", count);
    return count;
  }

  public List<EmployeeDTO> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAllWithoutDeleteAt();
    return employees.stream()
        .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
        .toList();
  }

  @Transactional(readOnly = true)
  public ListResponse getListEmployees(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Employee> employeePage = employeeRepository.findAllActiveEmployees(pageable);
    
    List<EmployeeDTO> contents = employeePage
        .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
        .getContent();
    
    int totalPages = employeePage.getTotalPages();
    long totalElements = employeePage.getTotalElements();
    
    return ListResponse.builder()
        .content(contents)
        .curPage(page)
        .curPageSize(size)
        .totalElements(totalElements)
        .totalPages(totalPages)
        .build();
  }

  @Transactional(readOnly = true)
  public EmployeeDTO getEmployeeById(UUID id) {
    Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee not found"));
    return modelMapper.map(employee, EmployeeDTO.class);
  }

  @Transactional(readOnly = true)
  public List<EmployeeDTO> getAllEmployeeByNameOrDepartment(String name, String department) {
    List<Employee> employees = employeeRepository.findAllByNameOrDepartment(name, department);
    if (employees.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
    
    return employees.stream()
        .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
        .toList();
  }


  @Transactional(readOnly = true)
  public ListResponse getEmployeeByNameOrDepartment(String name, String department, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Page<Employee> employeePage = employeeRepository.findByNameOrDepartment(name, department, pageable);
    if (employeePage.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
    
    List<EmployeeDTO> contents = employeePage
        .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
        .getContent();
    
    int totalPages = employeePage.getTotalPages();
    long totalElements = employeePage.getTotalElements();
    
    return ListResponse.builder()
        .content(contents)
        .curPage(page)
        .curPageSize(size)
        .totalElements(totalElements)
        .totalPages(totalPages)
        .build();
  }

  @Transactional(rollbackFor = Exception.class)
  public EmployeeDTO createEmployee(CreateEmployeeDTO request) {
    Employee employee = new Employee();
    employee.setId(utilityService.generateUUID());
    employee.setName(request.getName());
    employee.setEmail(request.getEmail());

    Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));

    employee.setDepartment(department);
    employeeRepository.save(employee);
    EmployeeDTO result = modelMapper.map(employee, EmployeeDTO.class);
    logger.info("Created employee: {}", result);
    return result;
  }

  @Transactional(rollbackFor = Exception.class)
  public EmployeeDTO updateEmployee(UpdateEmployeeDTO request) {
    Employee employee = employeeRepository.findById(request.getUpdateEmployeeId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
    employee.setName(request.getName());
    employee.setEmail(request.getEmail());

    Department department = departmentRepository.findById(request.getDepartmentId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found"));
    employee.setDepartment(department);
    employeeRepository.save(employee);

    EmployeeDTO result = modelMapper.map(employee, EmployeeDTO.class);
    logger.info("Updated employee: {}", result);
    return result;
  }

  @Transactional(rollbackFor = Exception.class)
  public void deleteEmployee(UUID id) {
    int result = employeeRepository.removeEmployeeById(id);
    if (result <= 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
  }
}

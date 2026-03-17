package com.learnjiava.employee_management.employee.entity;

import com.learnjiava.employee_management.common.entity.BaseEntity;
import com.learnjiava.employee_management.department.entity.Department;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@Getter
public class Employee extends BaseEntity {

  @Column(name = "name", nullable = false, length = 50)
  private String name;
  
  @Column(name = "email", nullable = false, unique = true, length = 100)
  private String email;
  
  @OneToOne()
  @JoinColumn(name = "department_id", referencedColumnName = "id")
  private Department department;
}

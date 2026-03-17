package com.learnjiava.employee_management.department.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "departments")
@Data
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public Integer id;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "created_at")
  public Date createdAt;

  @Column(name = "updated_at")
  public Date updatedAt;

  @Column(name = "deleted_at")
  public Date deletedAt;
}

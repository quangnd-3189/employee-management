package com.learnjiava.employee_management.user.entity;

import com.learnjiava.employee_management.common.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
  private String username;
  private String password;
  private String email;
  private String role; // 1: user, 2: admin
}

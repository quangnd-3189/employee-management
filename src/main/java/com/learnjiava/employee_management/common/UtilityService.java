package com.learnjiava.employee_management.common;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UtilityService {
  public UUID generateUUID() {
    return UUID.randomUUID();
  }

}

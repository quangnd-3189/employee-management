package com.learnjiava.employee_management.employee.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

  @Query("SELECT e FROM Employee e WHERE e.deletedAt IS NULL")
  List<Employee> findAllWithoutDeleteAt();

  @Query("SELECT e FROM Employee e WHERE e.deletedAt IS NULL")
  Page<Employee> findAllActiveEmployees(Pageable pageable);

  @Query("SELECT e FROM Employee e LEFT JOIN e.department d WHERE (e.name LIKE %?1% OR d.name = ?2) AND e.deletedAt IS NULL")
  Page<Employee> findByNameOrDepartment(String name, String department, Pageable pageable);

  //@Query("SELECT e FROM Employee e LEFT JOIN e.department d WHERE (e.name LIKE %?1% OR d.name = ?2) AND e.deletedAt IS NULL")
  @Query("""
    SELECT e
    FROM Employee e
    LEFT JOIN e.department d
    WHERE
    (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%')))
    AND
    (:department IS NULL OR LOWER(d.name) LIKE LOWER(CONCAT('%', :department, '%')))
    AND e.deletedAt IS NULL
    """)
  List<Employee> findAllByNameOrDepartment(
    @Param("name") String name,
  @Param("department") String department
  );

  @Modifying
  @Query("UPDATE Employee e SET e.deletedAt = CURRENT_TIMESTAMP WHERE e.id = ?1")
  int removeEmployeeById(UUID id);

  @Query("SELECT COUNT(e) FROM Employee e WHERE e.deletedAt IS NULL")
  int countByDeletedAtIsNull();
}

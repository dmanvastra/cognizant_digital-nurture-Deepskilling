package com.employee.EmployeeManagementSystem.repository;

import com.employee.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.EmployeeManagementSystem.repository.DepartmentRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}

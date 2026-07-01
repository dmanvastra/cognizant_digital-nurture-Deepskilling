package com.employee.EmployeeManagementSystem.service;

import com.employee.EmployeeManagementSystem.entity.Department;
import com.employee.EmployeeManagementSystem.entity.Employee;
import com.employee.EmployeeManagementSystem.projection.EmployeeProjection;
import com.employee.EmployeeManagementSystem.repository.DepartmentRepository;
import com.employee.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
                           DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }
    // CREATE EMPLOYEE
    public Employee saveEmployee(Employee employee) {

        if (employee.getDepartment() != null) {

            Long departmentId = employee.getDepartment().getId();

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            employee.setDepartment(department);
        }

        return employeeRepository.save(employee);
    }
    // BATCH INSERT
    public List<Employee> saveAllEmployees(List<Employee> employees) {

        for (Employee employee : employees) {

            if (employee.getDepartment() != null) {

                Long departmentId = employee.getDepartment().getId();

                Department department = departmentRepository.findById(departmentId)
                        .orElseThrow(() -> new RuntimeException("Department not found"));
                employee.setDepartment(department);
            }
        }
        return employeeRepository.saveAll(employees);
    }
    // GET ALL EMPLOYEES
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    // GET EMPLOYEE BY ID
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    // UPDATE EMPLOYEE
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());

        if (employee.getDepartment() != null) {

            Long departmentId = employee.getDepartment().getId();

            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new RuntimeException("Department not found"));

            existingEmployee.setDepartment(department);
        }
        return employeeRepository.save(existingEmployee);
    }
    // DELETE EMPLOYEE
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
    // DERIVED QUERY METHODS
    public Optional<Employee> getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }
    public List<Employee> getEmployeesByName(String name) {
        return employeeRepository.findByName(name);
    }
    public List<Employee> searchEmployees(String keyword) {
        return employeeRepository.findByNameContaining(keyword);
    }
    // JPQL QUERY METHODS
    public Employee getEmployeeByEmailUsingQuery(String email) {
        return employeeRepository.getEmployeeByEmailUsingQuery(email);
    }
    public List<Employee> getEmployeesByNameUsingQuery(String name) {
        return employeeRepository.getEmployeesByNameUsingQuery(name);
    }
    // PROJECTION
    public List<EmployeeProjection> getEmployeeProjection() {
        return employeeRepository.findAllProjectedBy();
    }
    // PAGINATION
    public Page<Employee> getEmployeesByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }
    // SORTING
    public List<Employee> getEmployeesSorted(String field) {
        return employeeRepository.findAll(Sort.by(field));
    }
    public Employee getEmployeeByEmailNative(String email) {
        return employeeRepository.getEmployeeByEmailNative(email);
    }
    // PAGINATION + SORTING
    public Page<Employee> getEmployeesByPageAndSort(int page,
                                                    int size,
                                                    String field) {
        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by(field)
        );
        return employeeRepository.findAll(pageable);
    }

}
package com.employee.EmployeeManagementSystem.controller;

import com.employee.EmployeeManagementSystem.entity.Employee;
import com.employee.EmployeeManagementSystem.projection.EmployeeProjection;
import com.employee.EmployeeManagementSystem.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create Employee
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Batch Insert
    @PostMapping("/batch")
    public List<Employee> saveAllEmployees(@RequestBody List<Employee> employees) {
        return employeeService.saveAllEmployees(employees);
    }
    @GetMapping("/native/email/{email}")
    public Employee getEmployeeByEmailNative(@PathVariable String email) {
        return employeeService.getEmployeeByEmailNative(email);
    }

    // Get All Employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get Employee By Id
    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // Update Employee
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id,
                                   @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    // Delete Employee
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee Deleted Successfully";
    }

    // Find By Email
    @GetMapping("/email/{email}")
    public Optional<Employee> getEmployeeByEmail(@PathVariable String email) {
        return employeeService.getEmployeeByEmail(email);
    }

    // Find By Name
    @GetMapping("/name/{name}")
    public List<Employee> getEmployeesByName(@PathVariable String name) {
        return employeeService.getEmployeesByName(name);
    }

    // Search By Keyword
    @GetMapping("/search/{keyword}")
    public List<Employee> searchEmployees(@PathVariable String keyword) {
        return employeeService.searchEmployees(keyword);
    }

    // JPQL Query - Email
    @GetMapping("/query/email/{email}")
    public Employee getEmployeeByEmailUsingQuery(@PathVariable String email) {
        return employeeService.getEmployeeByEmailUsingQuery(email);
    }

    // JPQL Query - Name
    @GetMapping("/query/name/{name}")
    public List<Employee> getEmployeesByNameUsingQuery(@PathVariable String name) {
        return employeeService.getEmployeesByNameUsingQuery(name);
    }

    // Projection
    @GetMapping("/projection")
    public List<EmployeeProjection> getEmployeeProjection() {
        return employeeService.getEmployeeProjection();
    }

    // Pagination
    @GetMapping("/page")
    public Page<Employee> getEmployeesByPage(@RequestParam int page,
                                             @RequestParam int size) {
        return employeeService.getEmployeesByPage(page, size);
    }

    // Sorting
    @GetMapping("/sort")
    public List<Employee> getEmployeesSorted(@RequestParam String field) {
        return employeeService.getEmployeesSorted(field);
    }

    // Pagination + Sorting
    @GetMapping("/page-sort")
    public Page<Employee> getEmployeesByPageAndSort(@RequestParam int page,
                                                    @RequestParam int size,
                                                    @RequestParam String field) {
        return employeeService.getEmployeesByPageAndSort(page, size, field);
    }
}
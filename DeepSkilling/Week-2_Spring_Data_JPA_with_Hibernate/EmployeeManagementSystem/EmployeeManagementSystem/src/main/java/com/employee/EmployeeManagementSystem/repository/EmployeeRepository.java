package com.employee.EmployeeManagementSystem.repository;
import org.springframework.data.jpa.repository.Query;
import com.employee.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import com.employee.EmployeeManagementSystem.projection.EmployeeProjection;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByName(String name);

    List<Employee> findByNameContaining(String keyword);
    @Query("SELECT e FROM Employee e WHERE e.email = ?1")
    Employee getEmployeeByEmailUsingQuery(String email);

    @Query("SELECT e FROM Employee e WHERE e.name = ?1")
    List<Employee> getEmployeesByNameUsingQuery(String name);
    List<EmployeeProjection> findAllProjectedBy();
    @Query(value = "SELECT * FROM employee WHERE email = ?1", nativeQuery = true)
    Employee getEmployeeByEmailNative(String email);

}

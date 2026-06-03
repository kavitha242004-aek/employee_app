package com.myapp.repository;

import com.myapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find by department
    List<Employee> findByDepartment(String department);

    // Find by email
    Optional<Employee> findByEmail(String email);
}

package com.myapp;

import com.myapp.model.Employee;
import com.myapp.repository.EmployeeRepository;
import com.myapp.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEmployees() {
        List<Employee> mockList = Arrays.asList(
            new Employee("Arun", "arun@test.com", "IT", 70000),
            new Employee("Priya", "priya@test.com", "HR", 60000)
        );
        when(employeeRepository.findAll()).thenReturn(mockList);

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testGetEmployeeById_Found() {
        Employee emp = new Employee("Arun", "arun@test.com", "IT", 70000);
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(emp));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals("Arun", result.get().getName());
    }

    @Test
    void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(99L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateEmployee() {
        Employee emp = new Employee("Rahul", "rahul@test.com", "Finance", 80000);
        when(employeeRepository.save(emp)).thenReturn(emp);

        Employee result = employeeService.createEmployee(emp);

        assertNotNull(result);
        assertEquals("Rahul", result.getName());
        verify(employeeRepository, times(1)).save(emp);
    }

    @Test
    void testDeleteEmployee_NotFound() {
        when(employeeRepository.existsById(99L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> employeeService.deleteEmployee(99L));
    }
}

package com.myapp;

import com.myapp.model.Employee;
import com.myapp.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(EmployeeRepository repository) {
        return args -> {
            // Pre-load sample data on startup
            repository.save(new Employee("Arun Kumar",    "arun@company.com",   "Engineering", 75000));
            repository.save(new Employee("Priya Sharma",  "priya@company.com",  "HR",           55000));
            repository.save(new Employee("Rahul Verma",   "rahul@company.com",  "Engineering", 80000));
            repository.save(new Employee("Sneha Patel",   "sneha@company.com",  "Finance",      60000));
            repository.save(new Employee("Vikram Singh",  "vikram@company.com", "Engineering", 90000));
            System.out.println("✅ Sample data loaded successfully!");
        };
    }
}

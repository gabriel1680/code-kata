package com.kata.out;

import com.kata.app.Employee;
import com.kata.app.EmployeeRepository;

import java.time.Instant;
import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final List<Employee> employees;

    public InMemoryEmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> getFor(Instant date) {
        return employees.stream().filter(e -> e.birthdate().equals(date)).toList();
    }
}

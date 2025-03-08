package com.kata;

import java.time.Instant;
import java.util.List;

class InMemoryEmployeeRepository implements EmployeeRepository {

    private final List<Employee> employees;

    InMemoryEmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> getFor(Instant date) {
        return employees.stream().filter(e -> e.birthdate().equals(date)).toList();
    }
}

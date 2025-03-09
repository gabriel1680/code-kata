package com.kata.out;

import com.kata.app.Employee;
import com.kata.app.EmployeeRepository;

import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final List<Employee> employees;

    public InMemoryEmployeeRepository(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public List<Employee> getFor(int month, int day) {
        return employees.stream().filter(e -> withBirthdateOn(e, month, day)).toList();
    }

    private static boolean withBirthdateOn(Employee e, int month, int day) {
        return e.birthdate().is(month, day);
    }
}

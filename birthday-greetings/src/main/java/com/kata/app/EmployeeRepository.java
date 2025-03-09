package com.kata.app;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getFor(int month, int day);
}

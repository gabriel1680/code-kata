package com.kata;

import java.time.Instant;
import java.util.List;

public interface EmployeeRepository {
    List<Employee> getFor(Instant date);
}

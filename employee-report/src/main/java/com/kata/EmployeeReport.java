package com.kata;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeReport {
    private final List<Employee> employees;

    public EmployeeReport(List<Employee> employees) {
        this.employees = employees;
    }

    public String getReport() {
        final var names = employees.stream()
                .map(Employee::name)
                .toList();
        final var formattedNames = names.stream()
                .collect(Collectors.joining());
        return "Emplyees Report:\n%sTotal employees: %s\n".formatted(formattedNames, names.size());
    }
}

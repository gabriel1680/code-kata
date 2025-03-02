package com.kata;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeReportTest {

    @Test
    void emptyReport() {
        final var employeeReport = new EmployeeReport(emptyList());
        final var report = "Emplyees Report:\nTotal employees: 0\n";
        assertEquals(report, employeeReport.getReport());
    }

    @Test
    void oneEmployeeReport() {
        final var employeeReport = new EmployeeReport(List.of(new Employee("Mike", 51)));
        final var report = "Emplyees Report:\n\tMike\nTotal employees: 1\n";
        assertEquals(report, employeeReport.getReport());
    }

    @ParameterizedTest
    @ValueSource(ints = {18, 19, 20})
    void filterFor18YearsOldOrOlder(int age) {
        final var employees = List.of(new Employee("Max", 17), new Employee("Mike", age));
        final var employeeReport = new EmployeeReport(employees);
        final var report = "Emplyees Report:\n\tMike\nTotal employees: 1\n";
        assertEquals(report, employeeReport.getReport());
    }

    @Test
    void sortingNamesInAscendingOrder() {
        final var employees = List.of(new Employee("Max", 17), new Employee("Sepp", 18), new Employee("Nina", 15), new Employee("Mike", 51));
        final var employeeReport = new EmployeeReport(employees);
        final var report = "Emplyees Report:\n\tMike\n\tSepp\nTotal employees: 2\n";
        assertEquals(report, employeeReport.getReport());
    }
}
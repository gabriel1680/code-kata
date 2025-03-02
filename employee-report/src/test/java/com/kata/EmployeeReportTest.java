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
}
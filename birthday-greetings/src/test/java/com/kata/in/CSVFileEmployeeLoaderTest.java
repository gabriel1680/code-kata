package com.kata.in;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVFileEmployeeLoaderTest {

    private static String getSourceFile(String filename) {
        return Paths.get("src", "test", "resources", filename).toString();
    }

    @Test
    void shouldThrowAParsingException() {
        final var employeeLoader = new CSVFileEmployeeLoader(getSourceFile("invalid-employees" +
                                                                                   ".csv"));
        assertThrows(RuntimeException.class, employeeLoader::loadEmployees);
    }

    @Test
    void shouldLoadEmployeesFromCsvFile() {
        final var employeeLoader = new CSVFileEmployeeLoader(getSourceFile("employees.csv"));
        final var employees = employeeLoader.loadEmployees();
        assertThat(employees, hasSize(2));
    }
}
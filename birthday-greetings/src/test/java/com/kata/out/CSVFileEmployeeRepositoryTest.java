package com.kata.out;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.kata.TestUtils.forDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CSVFileEmployeeRepositoryTest {

    private static String getSourceFile(String filename) {
        return Paths.get("src", "test", "resources", filename).toString();
    }

    @Test
    void shouldThrowAParsingException() {
        final var employeeRepository = new CSVFileEmployeeRepository(getSourceFile("invalid-employees.csv"));
        assertThrows(RuntimeException.class, employeeRepository::loadEmployees);
    }

    @Test
    void shouldLoadEmployeesFromCsvFile() {
        final var employeeRepository = new CSVFileEmployeeRepository(getSourceFile("employees.csv"));
        employeeRepository.loadEmployees();
        final var employees = employeeRepository.getFor(forDate("1982/10/08"));
        assertThat(employees, hasSize(1));
    }
}
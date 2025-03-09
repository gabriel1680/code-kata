package com.kata.out;

import com.kata.app.Birthdate;
import com.kata.app.Employee;
import com.kata.app.EmployeeRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileEmployeeRepository implements EmployeeRepository {
    private static final String COMMA_DELIMITER = ", ";

    private final String sourceFile;
    private final List<Employee> employees;

    public CSVFileEmployeeRepository(String sourceFile) {
        this.sourceFile = sourceFile;
        employees = new ArrayList<>();
    }

    @Override
    public List<Employee> getFor(int month, int day) {
        return employees.stream().filter(e -> e.birthdate().is(month, day)).toList();
    }

    public void loadEmployees() {
        final var records = parseCsvIntoRecords();
        // removes the header
        records.remove(0);
        for (var record : records) {
            employees.add(createEmployeeOf(record));
        }
    }

    private static Employee createEmployeeOf(List<String> record) {
        return new Employee(record.get(1), record.get(0), record.get(3), Birthdate.valueOf(record.get(2)));
    }

    private List<List<String>> parseCsvIntoRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            final List<List<String>> records = new ArrayList<>();
            for (String line; (line = reader.readLine()) != null; )
                records.add(parseLine(line));
            return records;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> parseLine(String line) {
        return Arrays.asList(line.split(COMMA_DELIMITER));
    }
}

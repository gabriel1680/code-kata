package com.kata.in;

import com.kata.app.Birthdate;
import com.kata.app.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVFileEmployeeLoader {
    private static final String COMMA_DELIMITER = ", ";

    private final String sourceFile;

    public CSVFileEmployeeLoader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public List<Employee> loadEmployees() {
        final var records = parseCsvIntoRecords();
        // removes the header
        records.remove(0);
        final var result = new ArrayList<Employee>();
        for (var record : records)
            result.add(createEmployeeOf(record));
        return result;
    }

    private static Employee createEmployeeOf(List<String> record) {
        return new Employee(record.get(1), record.get(0), record.get(3),
                            Birthdate.valueOf(record.get(2)));
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

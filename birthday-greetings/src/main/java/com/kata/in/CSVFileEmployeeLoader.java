package com.kata.in;

import com.kata.app.Birthdate;
import com.kata.app.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class CSVFileEmployeeLoader {
    private static final String COMMA_DELIMITER = ", ";
    private static final int CSV_HEADER = 1;

    private final String sourceFile;

    public CSVFileEmployeeLoader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public List<Employee> loadEmployees() {
        return parseCsvIntoRecords().stream()
                .map(CSVFileEmployeeLoader::createEmployeeOf)
                .toList();
    }

    private static Employee createEmployeeOf(List<String> record) {
        return new Employee(record.get(1), record.get(0), record.get(3),
                            Birthdate.valueOf(record.get(2)));
    }

    private List<List<String>> parseCsvIntoRecords() {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            return Stream.generate(createSupplierOf(reader))
                    .takeWhile(Objects::nonNull)
                    .skip(CSV_HEADER)
                    .map(CSVFileEmployeeLoader::parseLine)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Supplier<String> createSupplierOf(BufferedReader reader) {
        return () -> {
            try {
                return reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    private static List<String> parseLine(String line) {
        return Arrays.asList(line.split(COMMA_DELIMITER));
    }
}

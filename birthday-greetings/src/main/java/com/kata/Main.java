package com.kata;

import com.kata.app.BirthdayService;
import com.kata.in.CSVFileEmployeeLoader;
import com.kata.out.InMemoryEmailService;
import com.kata.out.InMemoryEmployeeRepository;

import java.nio.file.Paths;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        final var emailService = new InMemoryEmailService(System.out);
        final var filepath = Paths.get("src", "main", "resources", "employees.csv").toString();
        final var employeeLoader = new CSVFileEmployeeLoader(filepath);
        final var employeeRepository =
                new InMemoryEmployeeRepository(employeeLoader.loadEmployees());
        final var birthdayService = new BirthdayService(employeeRepository, emailService);
        birthdayService.sendGreetings(Instant.parse("2025-10-08T00:00:00.000Z"));
    }
}

package com.kata;

import com.kata.app.BirthdayService;
import com.kata.out.CSVFileEmployeeRepository;
import com.kata.out.InMemoryEmailService;

import java.nio.file.Paths;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        final var emailService = new InMemoryEmailService();
        final var filepath = Paths.get("src", "test", "resources", "employees.csv").toString();
        final var employeeRepository = new CSVFileEmployeeRepository(filepath);
        employeeRepository.loadEmployees();
        final var birthdayService = new BirthdayService(employeeRepository, emailService);
        birthdayService.sendGreetings(Instant.now());
    }
}

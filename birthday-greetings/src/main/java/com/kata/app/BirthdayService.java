package com.kata.app;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static java.time.ZoneOffset.UTC;

public class BirthdayService {

    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public BirthdayService(EmployeeRepository employeeRepository, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    public void sendGreetings(Instant birthdate) {
        getEmployeesToGreeting(birthdate).forEach(
                employee -> sendMailTo(employee.email(), employee.firstName()));
    }

    private List<Employee> getEmployeesToGreeting(Instant today) {
        final var birthdate = new Birthdate(LocalDate.ofInstant(today, UTC));
        final var month = birthdate.month();
        final var day = birthdate.day();
        if (day == 28 && month == 2) {
            final var employeesOf28 = employeeRepository.getFor(month, 28);
            final var employeesOf29 = employeeRepository.getFor(month, 29);
            return Stream.concat(employeesOf28.stream(), employeesOf29.stream()).toList();
        }
        return employeeRepository.getFor(month, day);
    }

    private void sendMailTo(String mail, String name) {
        final var message = new Message(mail, """
                Subject: Happy birthday!

                Happy birthday, dear %s!""".formatted(name));
        emailService.send(message);
    }
}

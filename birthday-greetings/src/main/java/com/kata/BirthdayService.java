package com.kata;

import java.time.Instant;

public class BirthdayService {

    private final EmployeeRepository employeeRepository;
    private final EmailService emailService;

    public BirthdayService(EmployeeRepository employeeRepository, EmailService emailService) {
        this.employeeRepository = employeeRepository;
        this.emailService = emailService;
    }

    public void sendGreetings(Instant birthdate) {
        final var employeesToGreeting = employeeRepository.getFor(birthdate);
        for (var employeeToGreeting : employeesToGreeting) {
            sendMailTo(employeeToGreeting.email(), employeeToGreeting.firstName());
        }
    }

    private void sendMailTo(String mail, String name) {
        final var message = new Message(mail, """
                Subject: Happy birthday!

                Happy birthday, dear %s!""".formatted(name));
        emailService.send(message);
    }
}

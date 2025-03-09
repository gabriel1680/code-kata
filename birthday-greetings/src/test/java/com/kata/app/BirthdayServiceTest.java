package com.kata.app;

import com.kata.out.InMemoryEmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class BirthdayServiceTest {

    private final Employee JOHN_DOE = new Employee("John", "Doe", "john.doe@foobar.com",
                                                   Birthdate.valueOf("1982/10/08"));
    private final Employee PETER_PARKER = new Employee("Peter", "Parker", "peter.parker@foobar.com",
                                                       Birthdate.valueOf("2001/08/10"));
    private final Employee SPIDER_MAN = new Employee("Spider", "Man", "spider.man@foobar.com",
                                                     Birthdate.valueOf(
            "2001/08/10"));
    private final Employee MARY = new Employee("Mary", "Ann", "mary.ann@foobar.com",
                                               Birthdate.valueOf("2000/02/29"));

    private SpyEmailService emailService;
    private EmployeeRepository employeeRepository;
    private BirthdayService sut;

    @BeforeEach
    void setUp() {
        emailService = new SpyEmailService();
        final var employees = List.of(JOHN_DOE, PETER_PARKER, SPIDER_MAN, MARY);
        employeeRepository = new InMemoryEmployeeRepository(employees);
        sut = new BirthdayService(employeeRepository, emailService);
    }

    @Test
    void shouldDoNothingWhenItsNobodyBirthday() {
        sut.sendGreetings(dateFor("1200/11/15"));
        assertThat(emailService.messagesSent(), hasSize(0));
    }

    @Test
    void shouldSendBirthdayGreetingsForOneEmployee() {
        sut.sendGreetings(JOHN_DOE.birthdate().toInstant());
        final var messages = emailService.messagesSent();
        final var mailString = """
                Subject: Happy birthday!

                Happy birthday, dear John!""";
        assertThat(messages, hasSize(1));
        assertThat(messages.get(0).to(), is(JOHN_DOE.email()));
        assertThat(messages.get(0).body(), is(mailString));
    }

    @Test
    void shouldSendBirthdayGreetingsForManyEmployees() {
        sut.sendGreetings(PETER_PARKER.birthdate().toInstant());
        final var messages = emailService.messagesSent();
        assertThat(messages, hasSize(2));
        assertThat(messages.get(0).to(), is(PETER_PARKER.email()));
        assertThat(messages.get(1).to(), is(SPIDER_MAN.email()));
    }

    @Test
    void shouldSendBirthdayGreetingsOn18FebWhenEmployeesBirthdayIs29Feb() {
        sut.sendGreetings(dateFor("2025/02/28"));
        final var messages = emailService.messagesSent();
        final var mailString = """
                Subject: Happy birthday!

                Happy birthday, dear Mary!""";
        assertThat(messages, hasSize(1));
        assertThat(messages.get(0).to(), is(MARY.email()));
        assertThat(messages.get(0).body(), is(mailString));
    }

    private static Instant dateFor(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant();
    }
}

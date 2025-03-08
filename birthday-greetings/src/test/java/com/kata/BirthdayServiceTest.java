package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;

public class BirthdayServiceTest {

    private final Employee JOHN_DOE = new Employee("John", "Doe", "john.doe@foobar.com", forDate("1982/10/08"));
    private final Employee PETER_PARKER = new Employee("Peter", "Parker", "peter.parker@foobar.com", forDate("2001/08/10"));
    private final Employee SPIDER_MAN = new Employee("Spider", "Man", "spider.man@foobar.com", forDate("2001/08/10"));

    private SpyEmailService emailService;
    private EmployeeRepository employeeRepository;
    private BirthdayService sut;

    private static Instant forDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atStartOfDay().toInstant(ZoneOffset.UTC);
    }

    @BeforeEach
    void setUp() {
        emailService = new SpyEmailService();
        final var employees = List.of(JOHN_DOE, PETER_PARKER, SPIDER_MAN);
        employeeRepository = new InMemoryEmployeeRepository(employees);
        sut = new BirthdayService(employeeRepository, emailService);
    }

    @Test
    void shouldDoNothingWhenItsNobodyBirthday() {
        sut.sendGreetings(forDate("1200/11/15"));
        assertThat(emailService.messagesSent(), hasSize(0));
    }

    @Captor
    private ArgumentCaptor<Message> captor;

    @Test
    void shouldSendBirthdayGreetingsForOneEmployee() {
        sut.sendGreetings(JOHN_DOE.birthdate());
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
        sut.sendGreetings(PETER_PARKER.birthdate());
        final var messages = emailService.messagesSent();
        assertThat(messages, hasSize(2));
        assertThat(messages.get(0).to(), is(PETER_PARKER.email()));
        assertThat(messages.get(1).to(), is(SPIDER_MAN.email()));
    }
}

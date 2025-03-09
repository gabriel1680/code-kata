package com.kata.app;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public record Birthdate(LocalDate date) {

    public static Birthdate valueOf(String dateString) {
        final var formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        final var date = LocalDate.parse(dateString, formatter);
        return new Birthdate(date);
    }

    public int day() {
        return date.getDayOfMonth();
    }

    public int month() {
        return date.getMonthValue();
    }

    public boolean is(int month, int day) {
        return day() == day && month() == month;
    }

    public Instant toInstant() {
        return date.atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}

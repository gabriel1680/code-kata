package com.kata;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TestUtils {
    public static Instant forDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd")).atStartOfDay().toInstant(ZoneOffset.UTC);
    }
}

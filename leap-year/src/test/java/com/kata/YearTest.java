package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearTest {

    private static boolean isLeapYear(int year1) {
        return new Year(year1).isLeap();
    }

    @Test
    void notLeapIsLeapYear_whenNotDivisibleBy4() {
        assertFalse(isLeapYear(1997));
    }

    @Test
    void isLeapIsLeapYear_whenDivisibleBy4() {
        assertTrue(isLeapYear(1996));
    }

    @Test
    void isLeapIsLeapYear_whenDivisibleBy400() {
        assertTrue(isLeapYear(1600));
    }

    @Test
    void notLeapIsLeapYear_whenNotDivisibleBy400ButIsFor100() {
        assertFalse(isLeapYear(1800));
    }
}

package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearTest {

    private Year year;

    @BeforeEach
    void setUp() {
        year = new Year();
    }

    @Test
    void notLeapYear_whenNotDivisibleBy4() {
        assertFalse(year.isLeap(1997));
    }

    @Test
    void isLeapYear_whenDivisibleBy4() {
        assertTrue(year.isLeap(1996));
    }

    @Test
    void isLeapYear_whenDivisibleBy400() {
        assertTrue(year.isLeap(1600));
    }

    @Test
    void notLeapYear_whenNotDivisibleBy400ButIsFor100() {
        assertFalse(year.isLeap(1800));
    }
}

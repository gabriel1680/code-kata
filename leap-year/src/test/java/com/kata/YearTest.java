package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearTest {

    @Test
    void notLeapYear_whenNotDivisibleBy4() {
        Year year = new Year(1997);
        assertFalse(year.isLeap());
    }

    @Test
    void isLeapYear_whenDivisibleBy4() {
        Year year = new Year(1996);
        assertTrue(year.isLeap());
    }

    @Test
    void isLeapYear_whenDivisibleBy400() {
        Year year = new Year(1600);
        assertTrue(year.isLeap());
    }

    @Test
    void notLeapYear_whenNotDivisibleBy400ButIsFor100() {
        Year year = new Year(1800);
        assertFalse(year.isLeap());
    }
}

package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class PrimeFactorsTest {

    private final PrimeFactors factors = new PrimeFactors();

    @Test
    void ofPrimeNumbers() {
        assertEquals(emptyList(), factors.of(1));
        assertEquals(List.of(2), factors.of(2));
        assertEquals(List.of(3), factors.of(3));
    }

    @Test
    void ofMultiplesOf2() {
        assertEquals(List.of(2, 2), factors.of(4));
        assertEquals(List.of(2, 2, 2), factors.of(8));
    }

    @Test
    void ofMultiplesOf3() {
        assertEquals(List.of(3, 3), factors.of(9));
        assertEquals(List.of(3, 3, 3), factors.of(27));
    }
}
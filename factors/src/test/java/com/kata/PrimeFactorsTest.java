package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FactorsTest {

    @Test
    void ofPrimeNumbers() {
        final var factors = new Factors();
        assertEquals(emptyList(), factors.of(1));
        assertEquals(List.of(2), factors.of(2));
        assertEquals(List.of(3), factors.of(3));
        assertEquals(List.of(5), factors.of(5));
    }

    @Test
    void ofMultiplesOf2() {
        final var factors = new Factors();
        assertEquals(List.of(2, 2), factors.of(4));
        assertEquals(List.of(2, 3), factors.of(6));
        assertEquals(List.of(2, 2, 2), factors.of(8));
    }

    @Test
    void ofMultiplesOf3() {
        final var factors = new Factors();
        assertEquals(List.of(3, 3), factors.of(9));
        assertEquals(List.of(3, 5), factors.of(15));
        assertEquals(List.of(3, 3, 3), factors.of(27));
        assertEquals(List.of(2, 3, 5, 7, 7, 13), factors.of(2*3*5*7*7*13));
    }
}
package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class PrimeFactorsTest {

    @Test
    void ofPrimeNumbers() {
        final var factors = new PrimeFactors();
        assertEquals(emptyList(), factors.of(1));
        assertEquals(List.of(2), factors.of(2));
        assertEquals(List.of(3), factors.of(3));
    }
}
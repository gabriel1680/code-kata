package com.kata;

import org.junit.jupiter.api.Test;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

class PrimeFactorsTest {

    @Test
    void ofPrimeNumbers() {
        final var primeFactors = new PrimeFactors();
        assertEquals(emptyList(), primeFactors.of(1));
    }
}
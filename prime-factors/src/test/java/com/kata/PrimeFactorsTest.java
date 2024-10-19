package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PrimeFactorsTest {

    private final PrimeFactors factors = new PrimeFactors();

    @Test
    void ofPrimeNumbers() {
        assertEquals(emptyList(), factors.of(1));
        assertEquals(List.of(2), factors.of(2));
        assertEquals(List.of(3), factors.of(3));
    }

    @ParameterizedTest
    @MethodSource("source")
    void ofMultiples(List<Integer> multiples, int number) {
        assertEquals(multiples, factors.of(number));
    }

    private static Stream<Arguments> source() {
        return Stream.of(
            Arguments.of(List.of(2, 2), 4),
            Arguments.of(List.of(2, 2, 2), 8),
            Arguments.of(List.of(3, 3), 9),
            Arguments.of(List.of(3, 3, 3), 27),
            Arguments.of(List.of(2, 2, 3, 5, 7, 7, 13), 2*2*3*5*7*7*13));
    }
}
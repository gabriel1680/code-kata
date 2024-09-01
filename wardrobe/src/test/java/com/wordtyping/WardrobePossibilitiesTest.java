package com.wordtyping;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WardrobePossibilitiesTest {
    @Test
    void calculate() {
        final var wardrobes = List.of(50, 75, 100, 120);
        var sut = new WardrobePossibilities(wardrobes);
        final var possibilities = List.of(
            List.of(50, 50, 50, 50, 50),
            List.of(50, 50, 50, 100),
            List.of(50, 50, 75, 75),
            List.of(50, 100, 100),
            List.of(75, 75, 100)
        );
        assertEquals(possibilities, sut.getPossibilitiesFor(250));
    }
}
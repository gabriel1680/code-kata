package com.wordtyping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WardrobeTest {

    private Wardrobe sut;

    @BeforeEach
    void setup() {
        final var measuresAndPrices = Map.of(
            50, 59.0,
            75, 62.0,
            100, 90.0,
            120, 110.0
        );
        sut = new Wardrobe(measuresAndPrices);
    }

    @Test
    void getCombinations() {
        final var possibilities = List.of(
            List.of(50, 50, 50, 50, 50), // 295
            List.of(50, 50, 50, 100), // 267
            List.of(50, 50, 75, 75), // 242
            List.of(50, 100, 100), // 239
            List.of(75, 75, 100) // 214
        );
        assertEquals(possibilities, sut.getCombinationsFor(250));
    }

    @Test
    void getCheapestCombination() {
        assertEquals(List.of(75, 75, 100), sut.getCheapestFor(250));
    }
}
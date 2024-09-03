package com.wordtyping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

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

    private static Stream<Arguments> makeWardrobesConfigurations() {
        final var wardrobeConfigurations = List.of(
            List.of(50, 50, 50, 50, 50), // 295
            List.of(50, 50, 50, 100), // 267
            List.of(50, 50, 75, 75), // 242
            List.of(50, 100, 100), // 239
            List.of(75, 75, 100) // 214
        );
        return Stream.of(Arguments.of(wardrobeConfigurations));
    }

    @ParameterizedTest
    @MethodSource("makeWardrobesConfigurations")
    void getCombinations(List<List<Integer>> configurations) {
        assertEquals(configurations, sut.getCombinationsFor(250));
    }

    @ParameterizedTest
    @MethodSource("makeWardrobesConfigurations")
    void getCheapestCombination(List<List<Integer>> configurations) {
        assertEquals(configurations.get(4), sut.getCheapestFor(250));
    }
}
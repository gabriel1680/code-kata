package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CoinChangerTest {

    private CoinChanger sut;

    @BeforeEach
    void setUp() {
        sut = new CoinChanger();
    }

    @ParameterizedTest
    @MethodSource("changeProvider")
    void change(List<Integer> exchangedCoins, int amount) {
        assertEquals(exchangedCoins, sut.exchange(amount));
    }

    private static List<Arguments> changeProvider() {
        return List.of(
            Arguments.of(List.of(0, 0, 0, 0), 0),
            Arguments.of(List.of(0, 0, 0, 1), 1),
            Arguments.of(List.of(0, 0, 0, 2), 1 + 1),
            Arguments.of(List.of(0, 0, 1, 0), 5),
            Arguments.of(List.of(0, 0, 1, 1), 5 + 1),
            Arguments.of(List.of(0, 1, 0, 0), 10),
            Arguments.of(List.of(0, 1, 1, 3), 10 + 5 + 3),
            Arguments.of(List.of(1, 0, 0, 0), 25),
            Arguments.of(List.of(1, 0, 0, 1), 25 + 1),
            Arguments.of(List.of(1, 1, 1, 1), 25 + 10 + 5 + 1)
        );
    }
}
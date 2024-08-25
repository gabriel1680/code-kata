package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CoinChangerTest {

    private CoinChanger sut;

    @BeforeEach
    void setUp() {
        sut = new CoinChanger();
    }

    @ParameterizedTest
    @MethodSource("changeProvider")
    void change(int[] exchangedCoins, int amount) {
        assertArrayEquals(exchangedCoins, sut.exchange(amount));
    }

    private static List<Arguments> changeProvider() {
        return List.of(
            Arguments.of(arrayOf(0, 0, 0, 0, 0), 0),
            Arguments.of(arrayOf(1, 0, 0, 0, 0), 1),
            Arguments.of(arrayOf(2, 0, 0, 0, 0), 1 + 1),
            Arguments.of(arrayOf(0, 1, 0, 0, 0), 5),
            Arguments.of(arrayOf(1, 1, 0, 0, 0), 5 + 1),
            Arguments.of(arrayOf(0, 0, 1, 0, 0), 10),
            Arguments.of(arrayOf(3, 1, 1, 0, 0), 10 + 5 + 3),
            Arguments.of(arrayOf(0, 0, 0, 1, 0), 25),
            Arguments.of(arrayOf(1, 0, 0, 1, 0), 25 + 1),
            Arguments.of(arrayOf(1, 1, 1, 1, 0), 25 + 10 + 5 + 1),
            Arguments.of(arrayOf(0, 0, 0, 0, 1), 100),
            Arguments.of(arrayOf(1, 1, 1, 1, 1), 100 + 25 + 10 + 5 + 1),
            Arguments.of(arrayOf(0, 0, 0, 0, 2), 100 + 100)
        );
    }

    private static int[] arrayOf(int ...n) {
        return Arrays.stream(n).toArray();
    }
}
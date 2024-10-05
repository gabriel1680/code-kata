package com.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YatzyTest {

    @ParameterizedTest
    @MethodSource("chancesSource")
    void chanceShouldSumAllDices(List<Integer> dices, int result) {
        final var yatzy = new Yatzy();
        assertEquals(result, yatzy.chance(dices));
    }

    private static Stream<Arguments> chancesSource() {
        return Stream.of(
            Arguments.of(List.of(1, 1, 1, 1, 1), 5),
            Arguments.of(List.of(1, 2, 3, 4, 5), 15)
        );
    }
}
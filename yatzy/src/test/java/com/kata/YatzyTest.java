package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class YatzyTest {

    private Yatzy yatzy;

    @BeforeEach
    void setUp() {
        yatzy = new Yatzy();
    }

    @ParameterizedTest
    @MethodSource("chancesSource")
    void chanceShouldSumAllDices(List<Integer> dices, int result) {
        assertEquals(result, yatzy.chance(dices));
    }

    private static Stream<Arguments> chancesSource() {
        return Stream.of(
            Arguments.of(List.of(1, 1, 1, 1, 1), 5),
            Arguments.of(List.of(1, 2, 3, 4, 5), 15)
        );
    }

    @ParameterizedTest
    @MethodSource("yatzySource")
    void yatzyShouldGet50WhenAllDicesAreEqualsOtherwise0(List<Integer> dices, int result) {
        assertEquals(result, yatzy.yatzy(dices));
    }

    private static Stream<Arguments> yatzySource() {
        return Stream.of(
            Arguments.of(List.of(2, 2, 2, 2, 2), 50),
            Arguments.of(List.of(1, 1, 1, 1, 1), 50),
            Arguments.of(List.of(1, 1, 1, 1, 5), 0),
            Arguments.of(List.of(1, 1, 1, 4, 5), 0),
            Arguments.of(List.of(1, 1, 3, 4, 5), 0),
            Arguments.of(List.of(1, 2, 3, 4, 5), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("pairSource")
    void pairShouldSumHighestPairsNumbers(List<Integer> dices, int result) {
        assertEquals(result, yatzy.pair(dices));
    }

    private static Stream<Arguments> pairSource() {
        return Stream.of(
            Arguments.of(List.of(1, 1, 2, 3, 4), 2),
            Arguments.of(List.of(1, 2, 2, 3, 4), 4),
            Arguments.of(List.of(1, 2, 5, 3, 4), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("smallStraightSource")
    void smallStraightShouldSum15WhenDicesAreALowerSequenceOtherwise0(List<Integer> dices, int result) {
        assertEquals(result, yatzy.smallStraight(dices));
    }

    private static Stream<Arguments> smallStraightSource() {
        return Stream.of(
            Arguments.of(List.of(1, 2, 3, 4, 5), 15),
            Arguments.of(List.of(1, 2, 2, 3, 4), 0),
            Arguments.of(List.of(1, 2, 3, 4, 6), 0),
            Arguments.of(List.of(2, 3, 4, 5, 6), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("largeStraightSource")
    void largeStraightShouldSum15WhenDicesAreAHigherSequenceOtherwise0(List<Integer> dices, int result) {
        assertEquals(result, yatzy.largeStraight(dices));
    }

    private static Stream<Arguments> largeStraightSource() {
        return Stream.of(
            Arguments.of(List.of(2, 3, 4, 5, 6), 20),
            Arguments.of(List.of(1, 2, 3, 4, 5), 0),
            Arguments.of(List.of(1, 2, 2, 3, 4), 0),
            Arguments.of(List.of(1, 2, 3, 4, 6), 0)
        );
    }

    @ParameterizedTest
    @MethodSource("fullHouseSource")
    void fullHouseSumAllDicesWhenTwoOfAKindAndThreeOfAnotherKindOtherwise0(List<Integer> dices, int result) {
        assertEquals(result, yatzy.fullHouse(dices));
    }

    private static Stream<Arguments> fullHouseSource() {
        return Stream.of(
            Arguments.of(List.of(1, 1, 2, 2, 3), 0),
            Arguments.of(List.of(1, 1, 2, 2, 2), 8)
        );
    }
}
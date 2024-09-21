package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithmeticsExpressionEvaluatorTest {

    private ArithmeticsExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new ArithmeticsExpressionEvaluator();
    }

    private void assertEvaluation(int result, String expression) {
        assertEquals(result, evaluator.evaluate(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", " 1 + (2 - 3)", "3 + (2 * 1)"})
    void noParamShouldThrow(String expression) {
        assertThrows(InvalidRecordException.class, () -> evaluator.evaluate(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {"()", "(())", "(()())"})
    void emptyBracesShouldReturn0(String expression) {
        assertEvaluation(0, expression);
    }

    @Test
    void evaluateShouldSum() {
        assertEvaluation(2, "(1 + 1)");
    }

    @Test
    void evaluateShouldMultiply() {
        assertEvaluation(6, "(3 * 2)");
    }

    @ParameterizedTest
    @MethodSource("evaluateMultipleExpressionsSource")
    void evaluateMultipleExpressions(int expected, String expression) {
        assertEvaluation(expected, expression);
    }

    private static Stream<Arguments> evaluateMultipleExpressionsSource() {
        return Stream.of(
            Arguments.of(10, "(2 * (3 + 2))"),
            Arguments.of(8, "(2 + (3 * 2))"),
            Arguments.of(101, "(1 + ((2 + 3) * (4 * 5)))")
//            Arguments.of(-165, "( 5 * ( 4 * ( 3 * ( 2 * ( 1 * 9 ) / 8 - 7 ) + 6 ) ) )")
        );
    }
}
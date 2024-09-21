package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithmeticsExpressionEvaluatorTest {

    private ArithmeticsExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new ArithmeticsExpressionEvaluator();
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", " 1 + (2 - 3)", "3 + (2 * 1)"})
    void noParamShouldThrow(String expression) {
        assertThrows(InvalidRecordException.class, () -> evaluator.evaluate(expression));
    }

    @ParameterizedTest
    @ValueSource(strings = {"()", "(())", "(()())"})
    void emptyBracesShouldReturn0(String expression) {
        assertEquals(0, evaluator.evaluate(expression));
    }

    @Test
    void evaluateShouldSum() {
        String expression = "(1 + 1)";
        assertEquals(2, evaluator.evaluate(expression));
    }

    @Test
    void evaluateShouldMultiply() {
        String expression = "(3 * 2)";
        assertEquals(6, evaluator.evaluate(expression));
    }

}
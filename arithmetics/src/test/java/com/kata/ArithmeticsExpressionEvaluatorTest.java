package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArithmeticsExpressionEvaluatorTest {

    private ArithmeticsExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new ArithmeticsExpressionEvaluator();
    }

    @Test
    void noParamShouldThrow() {
        String expression = "1";
        assertThrows(RuntimeException.class, () -> evaluator.evaluate(expression));
    }

    @Test
    void emptyBracesShouldReturn0() {
        String expression = "()";
        assertEquals(0, evaluator.evaluate(expression));
    }

    @Test
    void shouldSumExpressionInsideBraces() {
        String expression = "(1 + 1)";
        assertEquals(2, evaluator.evaluate(expression));
    }

    @Test
    void shouldMultiplyExpressionInsideBraces() {
        String expression = "(3 * 2)";
        assertEquals(6, evaluator.evaluate(expression));
    }

}
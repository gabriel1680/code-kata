package com.kata;

public class ArithmeticsExpressionEvaluator {

    public int evaluate(String expression) {
        final var tokens = RPNTokenizer.tokenize(expression);
        return RPNCalculator.calculate(tokens);
    }
}

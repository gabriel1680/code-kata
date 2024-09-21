package com.kata;

import java.util.Stack;

import static java.lang.Character.*;

public class ArithmeticsExpressionEvaluator {

    public int evaluate(String expression) {
        final var numbers = new Stack<Integer>();
        final var operators = new Stack<Character>();
        if (expression.charAt(0) != '(')
            throw new InvalidRecordException();
        for (char c : expression.toCharArray()) {
            if (c == ' ') {
                continue;
            } else if (isDigit(c)) {
                numbers.push(getNumericValue(c));
            } else if (c == ')') {
                if (numbers.isEmpty()) {
                    continue;
                }
                var b = numbers.pop();
                var a = numbers.pop();
                var op = operators.pop();
                numbers.push(applyOp(a, b, op));
                operators.pop();
            } else {
                operators.push(c);
            }
        }
        return numbers.isEmpty() ? 0 : numbers.get(0);
    }

    private int applyOp(int x, int y, char op) {
        return switch (op) {
            case '+' -> x + y;
            case '-' -> x - y;
            case '*' -> x * y;
            case '/' -> x / y;
            default -> 0;
        };
    }
}

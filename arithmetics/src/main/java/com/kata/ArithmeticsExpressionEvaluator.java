package com.kata;

import java.util.Stack;

import static java.lang.Character.*;

public class ArithmeticsExpressionEvaluator {

    public int evaluate(String expression) {
        if (isInvalid(expression))
            throw new InvalidRecordException();
        return compute(expression);
    }

    private static boolean isInvalid(String expression) {
        return expression.charAt(0) != '(';
    }

    private int compute(String expression) {
        final var numbers = new Stack<Integer>();
        final var operators = new Stack<Character>();
        for (char token : expression.toCharArray()) {
            computeFor(token, numbers, operators);
        }
        return !numbers.isEmpty() ? numbers.get(0) : 0;
    }

    private void computeFor(char token, Stack<Integer> numbers, Stack<Character> operators) {
        if (token == ' ') {
            return;
        } else if (isDigit(token)) {
            numbers.push(getNumericValue(token));
        } else if (token == ')') {
            if (numbers.isEmpty()) {
                return;
            }
            var b = numbers.pop();
            var a = numbers.pop();
            var op = operators.pop();
            numbers.push(applyOp(a, b, op));
            operators.pop();
        } else {
            operators.push(token);
        }
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

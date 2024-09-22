package com.kata;

import java.util.Stack;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;

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
            if (numbers.isEmpty()) return;
            final var b = numbers.pop();
            final var a = numbers.pop();
            final var operation = operators.pop();
            numbers.push(applyOperation(a, b, operation));
            operators.pop();
        } else {
            operators.push(token);
        }
    }

    private int applyOperation(int x, int y, char operation) {
        return switch (operation) {
            case '+' -> x + y;
            case '-' -> x - y;
            case '*' -> x * y;
            case '/' -> x / y;
            default -> 0;
        };
    }
}

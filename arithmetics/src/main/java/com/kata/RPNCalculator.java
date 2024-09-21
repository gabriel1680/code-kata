package com.kata;

import java.util.List;
import java.util.Stack;

import static java.lang.Character.getNumericValue;

class RPNCalculator {

    static int calculate(List<Character> tokens) {
        final var stack = new Stack<Character>();
        var sum = 0;
        for (char c : tokens) {
            if (Operation.isOperation(c)) {
                sum = performOperation(stack, Operation.of(c));
            } else {
                stack.push(c);
            }
        }
        return sum;
    }

    private static int performOperation(Stack<Character> stack, Operation operation) {
        return operation.execute(getNumericValue(stack.pop()),  getNumericValue(stack.pop()));
    }
}

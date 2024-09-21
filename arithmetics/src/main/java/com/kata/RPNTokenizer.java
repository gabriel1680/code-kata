package com.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RPNTokenizer {

    static List<Character> tokenize(String expression) {
        final var numbers = new Stack<Character>();
        final var operators = new Stack<Character>();
        if (expression.charAt(0) != '(')
            throw new InvalidRecordException();
        for (char c : expression.toCharArray()) {
            if (c == ' ' || c == '(' || c == ')') {
                continue;
            } else if (c == '+' || c == '*') {
                operators.push(c);
            } else {
                numbers.push(c);
            }
        }
        final var tokens = new ArrayList<Character>();
        var j = 0;
        for (var i = 0; i < numbers.size(); i += 2) {
            tokens.add(numbers.get(i));
            tokens.add(numbers.get(i + 1));
            tokens.add(operators.get(j));
            j++;
        }
        return tokens;
    }
}

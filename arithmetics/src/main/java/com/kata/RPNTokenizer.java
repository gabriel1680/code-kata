package com.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RPNTokenizer {

    static List<Character> tokenize(String expression) {
        if (!expression.contains("("))
            throw new RuntimeException();
        final var tokens = new ArrayList<Character>();
        final var numbers = new Stack<Character>();
        final var operators = new Stack<Character>();
        for (char c : expression.toCharArray()) {
            if (c == ' ' || c == '(' || c == ')') {
                continue;
            } else if (c == '+' || c == '*') {
                operators.push(c);
            } else {
                numbers.push(c);
            }
        }
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

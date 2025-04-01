package com.kata;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public class Interpreter {

    public List<String> interpret(String word) {
        if (word.isEmpty()) return emptyList();
        return isPalindrome(word, toReversed(word)) ?
            List.of(toReversed(word), "¡Bonita palabra!") :
            singletonList(toReversed(word));
    }

    private static String toReversed(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private static boolean isPalindrome(String word, String reversed) {
        return reversed.equals(word);
    }
}

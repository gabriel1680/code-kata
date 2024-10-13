package com.kata;

import java.util.List;

import static java.util.Collections.singletonList;

class Echo {
    public static List<String> of(String word) {
        final var reversedWord = toReversed(word);
        return isPalindrome(word, reversedWord) ?
                List.of(reversedWord, "¡Bonita palabra!") :
                singletonList(reversedWord);
    }

    private static String toReversed(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private static boolean isPalindrome(String word, String reversed) {
        return reversed.equals(word);
    }
}

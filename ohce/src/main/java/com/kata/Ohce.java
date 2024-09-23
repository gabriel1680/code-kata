package com.kata;

import java.util.List;

public class Ohce {

    private final Clock clock;

    public Ohce(Clock aClock) {
        clock = aClock;
    }

    public String start(String name) {
        final var hour = clock.getHour();
        if (hour > 20 || hour < 6)
            return "¡Buenas noches %s!".formatted(name);
        else if (hour >= 6 && hour <= 12)
            return "¡Buenos días %s!".formatted(name);
        else
            return "¡Buenas tardes %s!".formatted(name);
    }

    public List<String> echo(String word) {
        final var reversedWord = toReversed(word);
        return isPalindrome(word, reversedWord) ?
            List.of(reversedWord, "¡Bonita palabra!") :
            List.of(reversedWord);
    }

    private static String toReversed(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private static boolean isPalindrome(String word, String reversed) {
        return reversed.equals(word);
    }
}

package com.kata;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

public class Ohce {

    private final Clock clock;
    private String name;

    public Ohce(Clock aClock) {
        clock = aClock;
        name = "";
    }

    public String start(String aName) {
        name = aName;
        final var hour = clock.getHour();
        if (hour > 20 || hour < 6)
            return "¡Buenas noches %s!".formatted(name);
        else if (hour >= 6 && hour <= 12)
            return "¡Buenos días %s!".formatted(name);
        else
            return "¡Buenas tardes %s!".formatted(name);
    }

    public List<String> echo(String word) {
        if (word.equals("Stop!")) {
            return singletonList("Adios %s".formatted(name));
        }
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

package com.kata;

import java.util.List;

import static java.util.Collections.singletonList;

public class Ohce {

    private final Clock clock;
    private final String name;

    public Ohce(Clock aClock, String aName) {
        clock = aClock;
        name = aName;
    }

    public String start() {
        final var hour = clock.getHour();
        if (isNight(hour))
            return "¡Buenas noches %s!".formatted(name);
        else if (isMorning(hour))
            return "¡Buenos días %s!".formatted(name);
        else
            return "¡Buenas tardes %s!".formatted(name);
    }

    private static boolean isMorning(int hour) {
        return hour >= 6 && hour <= 12;
    }

    private static boolean isNight(int hour) {
        return hour > 20 || hour < 6;
    }

    public List<String> echo(String word) {
        if (isStop(word)) {
            return singletonList("Adios %s".formatted(name));
        }
        final var reversedWord = toReversed(word);
        return isPalindrome(word, reversedWord) ?
            List.of(reversedWord, "¡Bonita palabra!") :
            singletonList(reversedWord);
    }

    private static boolean isStop(String word) {
        return word.equals("Stop!");
    }

    private static String toReversed(String word) {
        return new StringBuilder(word).reverse().toString();
    }

    private static boolean isPalindrome(String word, String reversed) {
        return reversed.equals(word);
    }
}

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
        return buildSalutation(Salutation.at(hour));
    }

    private String buildSalutation(String salutation) {
        return "¡%s %s!".formatted(salutation, name);
    }

    public List<String> echo(String word) {
        if (isStop(word)) {
            return singletonList("Adios %s".formatted(name));
        }
        return Echo.of(word);
    }

    private static boolean isStop(String word) {
        return word.equals("Stop!");
    }
}

package com.kata;

import java.util.List;

import static java.util.Collections.singletonList;

public class Ohce {

    private final Clock clock;
    private final String name;
    private final Salutation salutation;
    private final Echo echo;

    public Ohce(Clock aClock, String aName) {
        clock = aClock;
        name = aName;
        salutation = new Salutation();
        echo = new Echo();
    }

    public String start() {
        return buildSalutation(salutation.at(clock.getHour()));
    }

    private String buildSalutation(String salutation) {
        return "¡%s %s!".formatted(salutation, name);
    }

    public List<String> echo(String word) {
        if (isStop(word)) {
            return singletonList("Adios %s".formatted(name));
        }
        return echo.of(word);
    }

    private static boolean isStop(String word) {
        return word.equals("Stop!");
    }
}

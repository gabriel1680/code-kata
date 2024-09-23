package com.kata;

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
}
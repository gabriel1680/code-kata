package com.kata;

import java.util.stream.Stream;

public class Main {

    private static final SystemClock clock = new SystemClock();

    public static void main(String[] args) {
        final var ohce = new Ohce(clock, "Test");
        System.out.println(ohce.start());
        Stream.of("osso", "Hola que tal")
            .map(ohce::echo)
            .forEach(item -> item.forEach(System.out::println));
    }
}
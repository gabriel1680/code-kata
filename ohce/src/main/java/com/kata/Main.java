package com.kata;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        final Clock clock = LocalDateTime.now()::getHour;
        final var ohce = new Ohce(clock, "Test");
        System.out.println(ohce.start());
        Stream.of("osso", "Hola que tal")
            .map(ohce::echo)
            .forEach(item -> item.forEach(System.out::println));
    }
}
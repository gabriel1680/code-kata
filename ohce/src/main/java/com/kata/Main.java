package com.kata;

public class Main {
    public static void main(String[] args) {
        final var clock = new SystemClock();
        final var ohce = new Ohce(clock, "Teste");
        System.out.println(ohce.start());
        for (var output : ohce.echo("osso")) {
            System.out.println(output);
        }
        for (var output : ohce.echo("Hola que tal")) {
            System.out.println(output);
        }
    }
}
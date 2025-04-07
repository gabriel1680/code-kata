package com.kata;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Clock clock = LocalDateTime.now()::getHour;
        var console = new Console(System.in, System.out);
        var ohce = new Ohce(console, clock);
        ohce.start(args[0]);
    }
}
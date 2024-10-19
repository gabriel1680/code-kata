package com.kata;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Clock clock = LocalDateTime.now()::getHour;
        final var io = new Console(System.out, new Scanner(System.in));
        final var app = new Ohce(clock, io);
        app.start(args[0]);
    }
}
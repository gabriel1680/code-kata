package com.kata;

public class Ohce {

    private final Clock clock;
    private final IODevice io;
    private final Salutation salutation;
    private final Echo echo;

    private String name = "";

    public Ohce(Clock aClock, IODevice io) {
        this.io = io;
        clock = aClock;
        salutation = new Salutation();
        echo = new Echo();
    }

    public void start(String aName) {
        name = aName;
        final var timelySalutation = buildSalutation(salutation.at(clock.getHour()));
        io.printLine(timelySalutation);
        while (true) {
            final var input = io.readLine();
            if (isStop(input)) {
                io.printLine("Adios %s".formatted(name));
                break;
            }
            echo.of(input).forEach(io::printLine);
        }
    }

    private String buildSalutation(String salutation) {
        return "¡%s %s!".formatted(salutation, name);
    }

    private static boolean isStop(String word) {
        return word.equals("Stop!");
    }
}

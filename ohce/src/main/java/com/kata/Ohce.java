package com.kata;

import java.util.stream.Stream;

public class Ohce {

    private final Clock clock;
    private final Console io;
    private final Salutation salutation;
    private final Echo echo;
    private final Presenter presenter;

    public Ohce(Clock aClock, Console console) {
        clock = aClock;
        io = console;
        presenter = new Presenter();
        salutation = new Salutation();
        echo = new Echo();
    }

    public void start(String aName) {
        final var timelySalutation = presenter.salute(salutation.at(clock.getHour()), aName);
        io.printLine(timelySalutation);
        Stream.generate(io::readLine)
            .takeWhile(this::isNotStop)
            .flatMap(this::doEcho)
            .forEach(io::printLine);
        io.printLine(presenter.goodbye(aName));
    }

    private boolean isNotStop(String input) {
        return !input.equals("Stop!");
    }

    private Stream<String> doEcho(String input) {
        return echo.of(input).stream().map(presenter::echo);
    }
}

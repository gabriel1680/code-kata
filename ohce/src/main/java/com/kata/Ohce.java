package com.kata;

public class Ohce {

    private final Clock clock;
    private final IODevice io;
    private final Salutation salutation;
    private final Echo echo;
    private final Presenter presenter;

    public Ohce(Clock aClock, IODevice ioDevice) {
        clock = aClock;
        io = ioDevice;
        presenter = new Presenter();
        salutation = new Salutation();
        echo = new Echo();
    }

    public void start(String aName) {
        final var timelySalutation = presenter.salute(salutation.at(clock.getHour()), aName);
        io.printLine(timelySalutation);
        while (true) {
            final var input = io.readLine();
            if (isStop(input)) {
                io.printLine(presenter.goodbye(aName));
                break;
            }
            echo.of(input).stream().map(presenter::echo).forEach(io::printLine);
        }
    }

    private static boolean isStop(String word) {
        return word.equals("Stop!");
    }
}

package com.kata;

import java.util.List;
import java.util.stream.Stream;

public class Ohce {

    private final Console console;
    private final Greeter greeter;
    private final Interpreter interpreter;

    public Ohce(Console console, Greeter greeter, Interpreter interpreter) {
        this.console = console;
        this.greeter = greeter;
        this.interpreter = interpreter;
    }

    public void start(String aName) {
        console.printLine(greeter.greet(aName));
        Stream.generate(console::readLine)
                .takeWhile(this::isNotStop)
                .map(interpreter::interpret)
                .flatMap(List::stream)
                .forEach(console::printLine);
        console.printLine("Adios " + aName);
    }

    private boolean isNotStop(String input) {
        return !input.equals("Stop!");
    }
}

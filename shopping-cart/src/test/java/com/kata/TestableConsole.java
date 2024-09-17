package com.kata;

public class TestableConsole extends Console {

    public String printedArgument = "";

    @Override
    protected void print(String s) {
        printedArgument = s;
    }
}

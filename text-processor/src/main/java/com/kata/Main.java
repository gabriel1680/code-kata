package com.kata;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        WordsPrinter printer = new WordsPrinter(console);
        TextProcessor textProcessor = new TextProcessor(printer);
        String text =
            "Hello, this is an example for you to practice. You should grab this text and make it as your test case.";
        textProcessor.analyse(text);
    }
}
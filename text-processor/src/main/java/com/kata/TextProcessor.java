package com.kata;

public class TextProcessor {

    private final MostUsedWordsProcessor mostUsedWordsProcessor;
    private final WordsPrinter printer;

    public TextProcessor(WordsPrinter printer) {
        this.printer = printer;
        this.mostUsedWordsProcessor = new MostUsedWordsProcessor();
    }

    public void analyse(String text) {
        throw new UnsupportedOperationException();
    }
}

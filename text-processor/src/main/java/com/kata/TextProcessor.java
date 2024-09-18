package com.kata;

public class TextProcessor {

    private final MostUsedWordsProcessor mostUsedWordsProcessor;

    public TextProcessor() {
        this.mostUsedWordsProcessor = new MostUsedWordsProcessor();
    }

    public void analyse(String text) {
        throw new UnsupportedOperationException();
    }
}

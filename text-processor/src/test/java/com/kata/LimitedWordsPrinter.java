package com.kata;

public class LimitedWordsPrinter extends WordsPrinter {

    private final int maxWordsToDisplay;

    public LimitedWordsPrinter(Console console, int maxWordsToDisplay) {
        super(console);
        this.maxWordsToDisplay = maxWordsToDisplay;
    }

    @Override
    protected int getMaxDisplay() {
        return maxWordsToDisplay;
    }
}
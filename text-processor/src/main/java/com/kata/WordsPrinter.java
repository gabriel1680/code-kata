package com.kata;

import java.util.List;

public class WordsPrinter {

    private final int MAX_WORDS_DISPLAY = 10;

    private final Console console;

    public WordsPrinter(Console console) {
        this.console = console;
    }

    public void print(List<String> words) {
        console.printLine("Those are the top 10 words used:");
        printWords(words);
        console.printLine(
            "The text has in total %d words".formatted(words.size()));
    }

    private void printWords(List<String> words) {
        if (words.isEmpty())
            console.printLine("-");
        else
            printNotEmptyWords(words);
    }

    private void printNotEmptyWords(List<String> words) {
        for (int i = 0; (i < words.size() && i < getMaxDisplay()); i++)
            console.printLine((i + 1) + ". " + words.get(i));
    }

    protected int getMaxDisplay() {
        return MAX_WORDS_DISPLAY;
    }
}

package com.kata;

public class TextProcessor {

    private final MostUsedWordsProcessor mostUsedWordsProcessor;
    private final TextParser parser;
    private final WordsPrinter printer;

    public TextProcessor(WordsPrinter printer) {
        this.printer = printer;
        this.mostUsedWordsProcessor = new MostUsedWordsProcessor();
        this.parser = new TextParser();
    }

    public void analyse(String text) {
        final var parsed = parser.parse(text);
        final var words = mostUsedWordsProcessor.process(parsed);
        printer.print(words, parsed.size());
    }
}

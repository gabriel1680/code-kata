package com.kata;

public class TextProcessor {

    private final WordsRanker ranker;
    private final TextParser parser;
    private final WordsPrinter printer;

    public TextProcessor(WordsPrinter printer) {
        this.printer = printer;
        ranker = new WordsRanker();
        parser = new TextParser();
    }

    public void analyse(String text) {
        final var parsed = parser.parse(text);
        final var ranked = ranker.rank(parsed);
        printer.print(ranked, parsed.size());
    }
}

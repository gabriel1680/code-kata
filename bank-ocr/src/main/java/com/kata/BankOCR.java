package com.kata;

public class BankOCR {

    private final FileReader fileReader;
    private final IO io;
    private final AccountNumberPresenter presenter;
    private final OCRFileParser fileParser;

    public BankOCR(FileReader fileReader, IO io, AccountNumberPresenter presenter) {
        this.fileReader = fileReader;
        this.io = io;
        this.presenter = presenter;
        this.fileParser = new OCRFileParser();
    }

    public void run() {
        final var filepath = io.read();
        final var content = fileReader.read(filepath);
        final var fileParser = this.fileParser;
        fileParser.parse(content).stream()
                .map(presenter::present)
                .forEach(io::print);
    }
}

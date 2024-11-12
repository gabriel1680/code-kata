package com.kata;

public class BankOCR {

    private final FileReader fileReader;
    private final IO io;
    private final AccountNumberPresenter presenter;
    private final OCRFileParser fileParser;

    public BankOCR(FileReader fileReader, IO io) {
        this.fileReader = fileReader;
        this.io = io;
        this.presenter = new AccountNumberPresenter();
        this.fileParser = new OCRFileParser();
    }

    public void run() {
        final var content = fileReader.read(io.read());
        fileParser.parse(content).stream()
                .map(presenter::present)
                .forEach(io::print);
    }
}

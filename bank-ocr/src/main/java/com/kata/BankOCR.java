package com.kata;

public class BankOCR {

    private final FileReader fileReader;
    private final IO io;
    private final AccountNumberPresenter presenter;

    public BankOCR(FileReader fileReader, IO io, AccountNumberPresenter presenter) {
        this.fileReader = fileReader;
        this.io = io;
        this.presenter = presenter;
    }

    public void run() {
        final var filepath = io.read();
        final var content = fileReader.read(filepath);
        final var fileParser = new OCRFileParser();
        for (var accountNumber : fileParser.parse(content)) {
            io.print(presenter.present(accountNumber));
        }
    }
}

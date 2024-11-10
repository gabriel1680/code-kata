package com.kata;

public class BankOCR {

    private final FileReader fileReader;
    private final IO io;

    public BankOCR(FileReader fileReader, IO io) {
        this.fileReader = fileReader;
        this.io = io;
    }

    public void run() {
        final var filepath = io.read();
        final var content = fileReader.read(filepath);
        final var parser = new AccountParser();
        final var accountNumber = parser.parse(content);
        io.print(accountNumber.toString());
    }
}

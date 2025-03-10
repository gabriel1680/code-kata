package com.kata.out;

import com.kata.app.EmailService;
import com.kata.app.Message;

import java.io.PrintStream;

public class InMemoryEmailService implements EmailService {

    private final PrintStream printStream;

    public InMemoryEmailService(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void send(Message message) {
        printStream.println(message.toString());
    }
}

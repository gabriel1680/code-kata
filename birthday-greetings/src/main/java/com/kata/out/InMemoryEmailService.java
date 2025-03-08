package com.kata.out;

import com.kata.app.EmailService;
import com.kata.app.Message;

public class InMemoryEmailService implements EmailService {

    @Override
    public void send(Message message) {
        System.out.println(message.toString());
    }
}

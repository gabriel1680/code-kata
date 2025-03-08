package com.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SpyEmailService implements EmailService {

    private final List<Message> messagesSent = new ArrayList<>();

    @Override
    public void send(Message message) {
        messagesSent.add(message);
    }

    public List<Message> messagesSent() {
        return Collections.unmodifiableList(messagesSent);
    }
}

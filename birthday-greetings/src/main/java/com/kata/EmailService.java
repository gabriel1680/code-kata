package com.kata;

@FunctionalInterface
public interface EmailService {
    void send(Message message);
}

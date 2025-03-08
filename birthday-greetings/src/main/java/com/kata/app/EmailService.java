package com.kata.app;

@FunctionalInterface
public interface EmailService {
    void send(Message message);
}

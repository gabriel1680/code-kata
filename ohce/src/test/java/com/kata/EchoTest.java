package com.kata;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EchoTest {

    private final Echo echo = new Echo();

    @Test
    void echoWordShouldReverseIt() {
        assertEquals(List.of("aloh"), echo.of("hola"));
    }

    @Test
    void echoWithPalindrome() {
        assertEquals(List.of("oto", "¡Bonita palabra!"), echo.of("oto"));
    }
}
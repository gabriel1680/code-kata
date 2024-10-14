package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EchoTest {

    private final Echo echo = new Echo();

    @Test
    void echoWordShouldReverseIt() {
        assertThat(echo.of("hola")).containsExactly("aloh");
        assertThat(echo.of("yeya")).containsExactly("ayey");
    }

    @Test
    void echoWithPalindrome() {
        assertThat(echo.of("oto")).containsExactly("oto", "¡Bonita palabra!");
    }
}
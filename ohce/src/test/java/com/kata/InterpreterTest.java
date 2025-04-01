package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InterpreterTest {

    private final Interpreter interpreter = new Interpreter();

    @Test
    void echoEmptyWordShouldReturnEmptyList() {
        assertThat(interpreter.interpret("")).isEmpty();
    }

    @Test
    void echoWordShouldReverseIt() {
        assertThat(interpreter.interpret("hola")).containsExactly("aloh");
        assertThat(interpreter.interpret("yeya")).containsExactly("ayey");
    }

    @Test
    void echoWithPalindrome() {
        assertThat(interpreter.interpret("oto")).containsExactly("oto", "¡Bonita palabra!");
    }
}
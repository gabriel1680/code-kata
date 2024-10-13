package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OhceTest {

    private TestableClock clock;
    private Ohce ohce;

    @BeforeEach
    void setUp() {
        clock = new TestableClock();
        ohce = new Ohce(clock, "Gabriel");
    }

    @Test
    void echoWordShouldReverseIt() {
        assertEquals(List.of("aloh"), ohce.echo("hola"));
    }

    @Test
    void echoWithPalindrome() {
        assertEquals(List.of("oto", "¡Bonita palabra!"), ohce.echo("oto"));
    }

    @Test
    void stop() {
        assertEquals(List.of("Adios Gabriel"), ohce.echo("Stop!"));
    }
}
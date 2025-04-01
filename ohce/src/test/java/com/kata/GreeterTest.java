package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class GreeterTest implements Clock {

    private static final String THOMAS_HOBBES = "Thomas Hobbes";

    private int currentHour;

    @Override
    public int getHour() {
        return currentHour;
    }

    private Greeter greeter;

    @BeforeEach
    void setUp() {
        greeter = new Greeter(this);
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 9, 11})
    void between6And12IsMorning(int hour) {
        currentHour = hour;
        assertThat(greeter.greet(THOMAS_HOBBES)).contains("Buenos días");
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 17, 18})
    void between12And20IsAfternoon(int hour) {
        currentHour = hour;
        assertThat(greeter.greet(THOMAS_HOBBES)).contains("Buenas tardes");
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 1, 5})
    void between20And6IsNight(int hour) {
        currentHour = hour;
        assertThat(greeter.greet(THOMAS_HOBBES)).contains("Buenas noches");
    }
}
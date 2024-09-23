package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class OhceTest {

    private TestableClock clock;
    private Ohce ohce;

    @BeforeEach
    void setUp() {
        clock = new TestableClock();
        ohce = new Ohce(clock);
    }

    @ParameterizedTest
    @ValueSource(ints = {21, 22, 23, 0, 1, 2, 3, 4, 5})
    void ohceBetween20And6(int hour) {
        clock.setHour(hour);
        assertEquals("¡Buenas noches Gabriel!", ohce.start("Gabriel"));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 7, 8, 9, 10, 11, 12})
    void ohceBetween6And12(int hour) {
        clock.setHour(hour);
        assertEquals("¡Buenos días Julia!", ohce.start("Julia"));
    }

    @ParameterizedTest
    @ValueSource(ints = {13, 14, 15, 16, 17, 18, 19, 20})
    void ohceBetween12And20(int hour) {
        clock.setHour(hour);
        assertEquals("¡Buenas tardes Nina!", ohce.start("Nina"));
    }
}
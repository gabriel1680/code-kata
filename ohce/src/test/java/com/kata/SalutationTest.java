package com.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class SalutationTest {

    private final Salutation salutation = new Salutation();

    @ParameterizedTest
    @ValueSource(ints = {20, 1, 5})
    void between20And6(int hour) {
        assertEquals("Buenas noches", salutation.at(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 9, 11})
    void between6And12(int hour) {
        assertEquals("Buenos días", salutation.at(hour));
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 17, 19})
    void between12And20(int hour) {
        assertEquals("Buenas tardes", salutation.at(hour));
    }
}
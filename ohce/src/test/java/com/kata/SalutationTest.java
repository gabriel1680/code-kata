package com.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SalutationTest {

    private final Salutation salutation = new Salutation();

    @ParameterizedTest
    @ValueSource(ints = {6, 9, 11})
    void between6And12IsMorning(int hour) {
        assertThat(salutation.at(hour)).isEqualTo("Buenos días");
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 17, 18})
    void between12And20IsAfternoon(int hour) {
        assertThat(salutation.at(hour)).isEqualTo("Buenas tardes");
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 1, 5})
    void between20And6IsNight(int hour) {
        assertThat(salutation.at(hour)).isEqualTo("Buenas noches");
    }
}
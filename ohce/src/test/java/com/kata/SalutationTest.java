package com.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class SalutationTest {

    private final Salutation salutation = new Salutation();

    @ParameterizedTest
    @ValueSource(ints = {20, 1, 5})
    void between20And6(int hour) {
        assertThat(salutation.at(hour)).isEqualTo("Buenas noches");
    }

    @ParameterizedTest
    @ValueSource(ints = {6, 9, 11})
    void between6And12(int hour) {
        assertThat(salutation.at(hour)).isEqualTo("Buenos días");
    }

    @ParameterizedTest
    @ValueSource(ints = {12, 17, 19})
    void between12And20(int hour) {
        assertThat(salutation.at(hour)).isEqualTo("Buenas tardes");
    }
}
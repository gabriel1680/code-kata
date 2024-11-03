package com.kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class EntryInterpreterTest {

    @ParameterizedTest
    @MethodSource("numbersSource")
    void executeShouldParseANumber(String entry, int accountDigit) {
        final var interpreter = new EntryInterpreter();
        assertThat(interpreter.execute(entry)).isEqualTo(accountDigit);
    }

    private static Stream<Arguments> numbersSource() {
        return Stream.of(
                Arguments.of("_\n| |\n|_|\n", 0),
                Arguments.of("\n|\n|\n", 1),
                Arguments.of("_\n_|\n|_\n", 2),
                Arguments.of("_\n_|\n_|\n", 3),
                Arguments.of("\n|_|\n|\n", 4),
                Arguments.of("_\n|_\n_|\n", 5),
                Arguments.of("_\n|_\n|_|\n", 6),
                Arguments.of("_\n|\n|\n", 7),
                Arguments.of("_\n|_|\n|_|\n", 8),
                Arguments.of("_\n|_|\n_|\n", 9)
        );
    }
}
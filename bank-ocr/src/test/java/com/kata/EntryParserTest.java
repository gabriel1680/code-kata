package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EntryParserTest {

    private final EntryParser parser = new EntryParser();

    @Test
    void shouldParseAllZeros() {
        final var content = """
                 _  _  _  _  _  _  _  _  _\s
                | || || || || || || || || |
                |_||_||_||_||_||_||_||_||_|
                                          \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("000000000");
    }

    @Test
    void shouldParseAllOnes() {
        final var content = """
                                          \s
                  |  |  |  |  |  |  |  |  |
                  |  |  |  |  |  |  |  |  |
                                          \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("111111111");
    }

    @Test
    void shouldParseAllTwos() {
        final var content = """
                 _  _  _  _  _  _  _  _  _\s
                 _| _| _| _| _| _| _| _| _|
                |_ |_ |_ |_ |_ |_ |_ |_ |_\s
                                          \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("222222222");
    }

    @Test
    void shouldParseAllThrees() {
        final var content = """
                 _  _  _  _  _  _  _  _  _\s
                 _| _| _| _| _| _| _| _| _|
                 _| _| _| _| _| _| _| _| _|
                                           \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("333333333");
    }


    @Test
    void shouldParseAllFours() {
        final var content = """
                                          \s
                |_||_||_||_||_||_||_||_||_|
                  |  |  |  |  |  |  |  |  |
                                          \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("444444444");
    }

    @Test
    void shouldParseAllFives() {
        final var content = """
                 _  _  _  _  _  _  _  _  _\s
                |_ |_ |_ |_ |_ |_ |_ |_ |_\s
                 _| _| _| _| _| _| _| _| _|
                                          \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("555555555");
    }

    @Test
    void shouldParseOneThroughNine() {
        final var content = """
                    _  _     _  _  _  _  _\s
                  | _| _||_||_ |_   ||_||_|
                  ||_  _|  | _||_|  ||_| _|
                                          \s
                """;
        assertThat(parser.parse(content).toString())
                .isEqualTo("123456789");
    }
}
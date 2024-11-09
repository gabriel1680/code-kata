package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccountParserTest {

    private final AccountParser parser = new AccountParser();

    @Test
    void shouldParseAllZeros() {
        final var content = """
                 _  _  _  _  _  _  _  _  _\s
                | || || || || || || || || |
                |_||_||_||_||_||_||_||_||_|
                                          \s
                """;
        assertThat(parser.parse(content))
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
        assertThat(parser.parse(content))
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
        assertThat(parser.parse(content))
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
        assertThat(parser.parse(content))
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
        assertThat(parser.parse(content))
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
        assertThat(parser.parse(content))
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
        assertThat(parser.parse(content))
                .isEqualTo("123456789");
    }
}
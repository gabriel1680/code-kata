package com.kata;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContentParserTest {

    private final ContentParser parser = new ContentParser();

    @Test
    void shouldParseAllZeros() {
        final var content = """
                 _  _  _  _  _  _  _  _  _\s
                | || || || || || || || || |
                |_||_||_||_||_||_||_||_||_|
                                          \s
                """;
        assertThat(parser.parse(content))
                .containsExactly(0, 0, 0, 0, 0, 0, 0, 0, 0);
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
                .containsExactly(1, 1, 1, 1, 1, 1, 1, 1, 1);
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
                .containsExactly(2, 2, 2, 2, 2, 2, 2, 2, 2);
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
                .containsExactly(3, 3, 3, 3, 3, 3, 3, 3, 3);
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
                .containsExactly(4, 4, 4, 4, 4, 4, 4, 4, 4);
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
                .containsExactly(5, 5, 5, 5, 5, 5, 5, 5, 5);
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
                .containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
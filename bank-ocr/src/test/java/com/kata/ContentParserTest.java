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
}
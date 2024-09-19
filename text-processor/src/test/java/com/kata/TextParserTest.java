package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextParserTest {

    private TextParser parser;

    @BeforeEach
    void setUp() {
        parser = new TextParser();
    }

    @Test
    void parseWordsWithMultipleOccurrences() {
        assertEquals(List.of("x",  "y"), parser.parse("x y"));
    }

    @Test
    void parseWordsIgnoringCase() {
        assertEquals(List.of("x", "x"), parser.parse("x X"));
    }

    @Test
    void parseWordsIgnoringOtherChars() {
        assertEquals(List.of("x", "x", "y"), parser.parse("x, x. y"));
    }
}
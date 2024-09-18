package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MostUsedWordsProcessorTest {

    private MostUsedWordsProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new MostUsedWordsProcessor();
    }

    @Test
    void processWords_whenEmpty() {
        assertEquals(emptyList(), processor.process(""));
    }

    @Test
    void processWords_whenOnlyOneWord() {
        assertEquals(List.of("x"), processor.process("x"));
    }

    @Test
    void processWords_whenAllDifferent() {
        assertEquals(List.of("x", "y"), processor.process("x y"));
    }

    @Test
    void processWordsWithMultipleOccurrences() {
        assertEquals(List.of("x", "y"), processor.process("x x y"));
    }

    @Test
    void processWordsIgnoringCase() {
        assertEquals(List.of("x", "y"), processor.process("x X y"));
    }

    @Test
    void processWordsInOrder() {
        assertEquals(List.of("y", "x"), processor.process("x y y"));
    }

    @Test
    void processWordsInOrderWithSameOccurrences() {
        assertEquals(List.of("x", "y"), processor.process("x x y y"));
    }
}
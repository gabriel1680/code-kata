package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MostUsedWordsProcessorTest {

    private MostUsedWordsProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new MostUsedWordsProcessor();
    }

    private void assertProcessed(List<String> expected, String wordsAsString) {
        assertEquals(expected, processor.process(toWordsList(wordsAsString)));
    }

    private static List<String> toWordsList(String wordsAsString) {
        return Arrays.stream(wordsAsString.split(" ")).toList();
    }

    @Test
    void processWords_whenEmpty() {
        assertEquals(emptyList(), processor.process(emptyList()));
    }

    @Test
    void processWords_whenOnlyOneWord() {
        assertProcessed(List.of("x"), "x");
    }

    @Test
    void processWords_whenAllDifferent() {
        assertProcessed(List.of("x", "y"), "x y");
    }

    @Test
    void processWordsInOrder() {

    }

    @Test
    void processWordsInOrderWithSameOccurrences() {
        assertProcessed(List.of("x", "y"), "x x y y");
    }
}
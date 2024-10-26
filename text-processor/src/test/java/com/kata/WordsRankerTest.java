package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordsRankerTest {

    private WordsRanker processor;

    @BeforeEach
    void setUp() {
        processor = new WordsRanker();
    }

    private void assertProcessed(List<String> expected, String wordsAsString) {
        assertEquals(expected, processor.rank(toWordsList(wordsAsString)));
    }

    private static List<String> toWordsList(String wordsAsString) {
        return Arrays.stream(wordsAsString.split(" ")).toList();
    }

    @Test
    void rankWords_whenEmpty() {
        assertEquals(emptyList(), processor.rank(emptyList()));
    }

    @Test
    void rankWords_whenOnlyOneWord() {
        assertProcessed(List.of("x"), "x");
    }

    @Test
    void rankWords_whenAllDifferent() {
        assertProcessed(List.of("x", "y"), "x y");
    }

    @Test
    void rankWordsInOrder() {
        assertProcessed(List.of("x", "y"), "y x");
        assertProcessed(List.of("x", "y"), "x y");
        assertProcessed(List.of("y", "x"), "x y y");
    }

    @Test
    void rankWordsInOrderWithSameOccurrences() {
        assertProcessed(List.of("x", "y"), "x x y y");
    }
}
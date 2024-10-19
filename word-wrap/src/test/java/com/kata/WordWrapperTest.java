package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordWrapperTest {

    private final WordWrapper wrapper = new WordWrapper();

    @Test
    void wrap() {
        assertEquals("x", wrapper.wrap("x", 1));
        assertEquals("y", wrapper.wrap("y", 1));
        assertEquals("x\nx", wrapper.wrap("xx", 1));
        assertEquals("xx\nx", wrapper.wrap("xxx", 2));
        assertEquals("x\nx\nx", wrapper.wrap("xxx", 1));
        assertEquals("x\nx", wrapper.wrap("x x", 2));
        assertEquals("xx\nxx", wrapper.wrap("xx xx", 4));
    }

    @Test
    void acceptance() {
        final var s = "Four score and seven years ago our fathers brought forth upon this continent a new nation conceived in liberty and dedicated to the proposition that all men are created equal";
        final var expected = "Four score and seven years ago\nour fathers brought forth upon\nthis continent a new nation\nconceived in liberty and\ndedicated to the proposition\nthat all men are created equal";
        assertEquals(expected, wrapper.wrap(s, 30));
    }
}
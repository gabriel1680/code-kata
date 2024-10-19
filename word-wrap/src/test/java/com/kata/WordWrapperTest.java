package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WordWrapperTest {

    @Test
    void wrap() {
        final var wrapper = new WordWrapper();
        assertEquals("x", wrapper.wrap("x", 1));
        assertEquals("y", wrapper.wrap("y", 1));
        assertEquals("x\nx", wrapper.wrap("xx", 1));
        assertEquals("xx\nx", wrapper.wrap("xxx", 2));
        assertEquals("x\nx\nx", wrapper.wrap("xxx", 1));
        assertEquals("x\nx", wrapper.wrap("x x", 2));
        assertEquals("xx\nxx", wrapper.wrap("xx xx", 4));
    }
}
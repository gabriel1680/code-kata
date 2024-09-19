package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void newStackShouldBeEmpty() {
        Stack stack = new Stack();
        assertTrue(stack.isEmpty());
        assertEquals(0, stack.size());
    }

    @Test
    void pushOneNotEmpty() {
        Stack stack = new Stack();
        stack.push(1);
        assertEquals(1, stack.size());
        assertFalse(stack.isEmpty());
    }
}
package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private Stack stack;

    @BeforeEach
    void setUp() {
        stack = new Stack(2);
    }

    @Test
    void createStackWithNegativeNumber_shouldThrow() {
        assertThrows(InvalidCapacity.class, () -> new Stack(-1));
    }

    @Test
    void newStack_isEmpty() {
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushOne_notEmpty() {
        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test()
    void popEmptyStack_shouldThrowError() {
        assertThrows(UnderflowException.class, stack::pop);
    }

    @Test
    void pushOne_incrementSize() {
        stack.push(1);
        assertEquals(1, stack.size());
    }

    @Test
    void pushOneThenPop_decrementSize() {
        stack.push(1);
        stack.pop();
        assertEquals(0, stack.size());
    }

    @Test
    void pushOneThenPop() {
        stack.push(1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void pushOneThenPopIt() {
        stack.push(1);
        assertEquals(1, stack.pop());
    }

    @Test
    void pushTwoThenPopLast() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void pushMoreThanCapacity_shouldThrowError() {
        stack.push(1);
        stack.push(2);
        assertThrows(OverflowException.class, () -> stack.push(2));
    }
}
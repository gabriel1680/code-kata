package com.kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleTest {

    @Test
    void printLine() {
        TestableConsole console = new TestableConsole();
        console.printLine("Test 123");
        assertEquals("Test 123", console.printedArgument);
    }
}
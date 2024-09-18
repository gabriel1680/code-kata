package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WordsPrinterTest {

    @Mock
    private Console console;

    private WordsPrinter printer;

    @BeforeEach
    void setUp() {
        printer = new WordsPrinter(console);
    }

    @Test
    void printListOfWords_whenEmpty() {
        printer.print(emptyList());
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Those are the top 10 words used:");
        inOrder.verify(console).printLine("-");
        inOrder.verify(console).printLine("The text has in total 0 words");
    }

    @Test
    void printListOfWords_whenOnlyOneWord() {
        printer.print(List.of("a"));
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Those are the top 10 words used:");
        inOrder.verify(console).printLine("1. a");
        inOrder.verify(console).printLine("The text has in total 1 words");
    }

    @Test
    void printListOfWords_whenLessThan10() {
        printer.print(List.of("a", "b"));
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Those are the top 10 words used:");
        inOrder.verify(console).printLine("1. a");
        inOrder.verify(console).printLine("2. b");
        inOrder.verify(console).printLine("The text has in total 2 words");
    }

    @Test
    void printListOfWords_whenMoreThan10() {
        final var maxWordsToDisplay = 2;
        printer = new LimitedWordsPrinter(console, maxWordsToDisplay);
        printer.print(List.of("a", "b", "c"));
        InOrder inOrder = inOrder(console);
        inOrder.verify(console).printLine("Those are the top 10 words used:");
        inOrder.verify(console).printLine("1. a");
        inOrder.verify(console).printLine("2. b");
        inOrder.verify(console).printLine("The text has in total 3 words");
        verify(console, times(4)).printLine(anyString());
    }
}
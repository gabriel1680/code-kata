package com.kata;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

class TextProcessorTest {

    @Mock
    private Console console;

    @Test
    void givenAText_whenProcess_shouldReturnTheOrderedListOfMostUsedWordsAndWordsCount() {
        WordsPrinter printer = new WordsPrinter(console);
        TextProcessor textProcessor = new TextProcessor(printer);
        String text =
                "Hello, this is an example for you to practice. You should grab this text and make it as your test case.";
        textProcessor.analyse(text);
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("Those are the top 10 words used:");
        inOrder.verify(console).printLine("1. you");
        inOrder.verify(console).printLine("2. this");
        inOrder.verify(console).printLine("3. your");
        inOrder.verify(console).printLine("4. to");
        inOrder.verify(console).printLine("5. text");
        inOrder.verify(console).printLine("6. test");
        inOrder.verify(console).printLine("7. should");
        inOrder.verify(console).printLine("8. practice");
        inOrder.verify(console).printLine("9. make");
        inOrder.verify(console).printLine("1o. it");
        inOrder.verify(console).printLine("The text has in total 21 words");
    }
}

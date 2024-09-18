package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
        inOrder.verify(console).printLine("1. this");
        inOrder.verify(console).printLine("2. you");
        inOrder.verify(console).printLine("3. practice");
        inOrder.verify(console).printLine("4. test");
        inOrder.verify(console).printLine("5. for");
        inOrder.verify(console).printLine("6. is");
        inOrder.verify(console).printLine("7. your");
        inOrder.verify(console).printLine("8. it");
        inOrder.verify(console).printLine("9. an");
        inOrder.verify(console).printLine("10. example");
        inOrder.verify(console).printLine("The text has in total 21 words");
    }
}

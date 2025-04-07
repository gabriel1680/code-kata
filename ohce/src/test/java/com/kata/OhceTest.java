package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OhceTest {

    @Mock
    private Console console;
    private Clock clock;

    private Ohce sut;

    @BeforeEach
    void setUp() {
        clock = () -> 6;
        sut = new Ohce(console, clock);
    }

    @Test
    void greetAtMorningThenStop() {
        when(console.readLine()).thenReturn("Stop!");
        sut.start("Gabriel");
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("¡Buenos días Gabriel!");
        inOrder.verify(console).printLine("Adios Gabriel");
    }

    @Test
    void greetAtMorningThenEchoAWordThenStop() {
        when(console.readLine()).thenReturn("hola", "Stop!");
        sut.start("Gabriel");
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("¡Buenos días Gabriel!");
        inOrder.verify(console).printLine("aloh");
        inOrder.verify(console).printLine("Adios Gabriel");
    }
}
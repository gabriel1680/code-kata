package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OhceTest {

    @Mock
    private Greeter greeter;
    @Mock
    private Interpreter interpreter;
    @Mock
    private Console console;

    private Ohce sut;

    @BeforeEach
    void setUp() {
        sut = new Ohce(console, greeter, interpreter);
    }

    @Test
    void greetAtMorningThenStop() {
        when(console.readLine()).thenReturn("Stop!");
        when(greeter.greet(anyString())).thenReturn("¡Buenos días Gabriel!");
        sut.start("Gabriel");
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("¡Buenos días Gabriel!");
        inOrder.verify(console).printLine("Adios Gabriel");
    }

    @Test
    void greetAtMorningThenEchoAWordThenStop() {
        when(console.readLine()).thenReturn("hola", "Stop!");
        when(greeter.greet(anyString())).thenReturn("¡Buenos días Gabriel!");
        when(interpreter.interpret(anyString())).thenReturn(List.of("aloh"));
        sut.start("Gabriel");
        InOrder inOrder = Mockito.inOrder(console);
        inOrder.verify(console).printLine("¡Buenos días Gabriel!");
        inOrder.verify(console).printLine("aloh");
        inOrder.verify(console).printLine("Adios Gabriel");
    }
}
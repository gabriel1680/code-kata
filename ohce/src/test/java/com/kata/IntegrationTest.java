package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IntegrationTest {

    @Mock
    private PrintStream out;
    private InputStream in;
    private Clock clock;
    private Ohce sut;

    @BeforeEach
    void setUp() {
        List<String> inputs = List.of("hola", "oto", "Stop!");
        in = new ByteArrayInputStream(String.join("\n", inputs).getBytes());
        clock = () -> 20;
        var console = new Console(in, out);
        var greeter = new Greeter(clock);
        var interpreter = new Interpreter();
        sut = new Ohce(console, greeter, interpreter);
    }

    @Test
    void integration() {
        sut.start("Gabriel");
        InOrder inOrder = Mockito.inOrder(out);
        inOrder.verify(out).println("¡Buenas noches Gabriel!");
        inOrder.verify(out).println("aloh");
        inOrder.verify(out).println("oto");
        inOrder.verify(out).println("¡Bonita palabra!");
        inOrder.verify(out).println("Adios Gabriel");
    }
}
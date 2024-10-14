package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OhceTest {

    @Mock
    private Clock clock;

    @Mock
    private IODevice io;

    @InjectMocks
    private Ohce sut;

    @Test
    void givenAUserAtNight_whenStartTheAppAndSendSomeWords_thenShouldGetSalutationEchoAndGoodBye() {
        when(clock.getHour()).thenReturn(20);
        when(io.readLine())
            .thenReturn("hola")
            .thenReturn("oto")
            .thenReturn("Stop!");
        sut.start("Gabriel");
        InOrder inOrder = Mockito.inOrder(io);
        inOrder.verify(io).printLine("¡Buenas noches Gabriel!");
        inOrder.verify(io).printLine("aloh");
        inOrder.verify(io).printLine("oto");
        inOrder.verify(io).printLine("¡Bonita palabra!");
        inOrder.verify(io).printLine("Adios Gabriel");
    }
}
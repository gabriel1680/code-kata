package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class IntegrationTest {

    @Mock
    private PrintStream out;

    private Anagram anagram;

    @BeforeEach
    void setUp() {
        anagram = new Anagram(out, new AnagramsProvider(), new AnagramSolver());
    }

    @Test
    void acceptance() {
        anagram.run("documenting");
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).println("documenting");
        inOrder.verify(out).println("gnitnemucod");
        inOrder.verify(out).println("mnietgnuodc");
    }
}
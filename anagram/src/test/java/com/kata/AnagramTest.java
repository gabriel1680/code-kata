package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.PrintStream;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnagramTest {

    @Mock
    private PrintStream out;
    @Mock
    private AnagramsProvider provider;
    @Mock
    private AnagramSolver solver;

    private Anagram anagram;

    @BeforeEach
    void setUp() {
        anagram = new Anagram(out, provider, solver);
    }

    @Test
    void readFromFile() {
        when(provider.provide()).thenReturn(List.of("ab", "ba", "cd"));
        when(solver.solve(anyString(), any())).thenReturn(List.of("ab", "ba"));
        anagram.run("ab");
        InOrder inOrder = inOrder(out);
        inOrder.verify(out).println("ab");
        inOrder.verify(out).println("ba");
    }
}
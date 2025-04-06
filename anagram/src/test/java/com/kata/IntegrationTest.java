package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class IntegrationTest {

    @Mock
    private PrintStream out;

    private Anagram anagram;

    @BeforeEach
    void setUp() throws IOException {
        final var resource = getClass().getClassLoader().getResource("test-file.txt");
        final var reader = Files.newBufferedReader(Path.of(resource.getPath()));
        final var provider = new AnagramsProvider(reader);
        final var solver = new AnagramSolver();
        anagram = new Anagram(out, provider, solver);
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
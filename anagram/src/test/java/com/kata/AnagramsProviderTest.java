package com.kata;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnagramsProviderTest {

    @Mock
    private BufferedReader reader;

    @Test
    void readFromFile() {
        when(reader.lines()).thenReturn(Stream.of("a", "b"));
        var provider = new AnagramsProvider(reader);
        assertThat(provider.provide())
                .containsExactly("a", "b");
    }
}
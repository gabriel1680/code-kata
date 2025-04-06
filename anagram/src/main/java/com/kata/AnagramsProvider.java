package com.kata;

import java.io.BufferedReader;
import java.util.List;

public class AnagramsProvider {

    private final BufferedReader reader;

    public AnagramsProvider(BufferedReader reader) {
        this.reader = reader;
    }

    public List<String> provide() {
        return reader.lines().toList();
    }
}

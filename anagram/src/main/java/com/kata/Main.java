package com.kata;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        final var path = Paths.get("src", "main", "resources", "anagrams.txt");
        final var reader = Files.newBufferedReader(path);
        final var provider = new AnagramsProvider(reader);
        final var solver = new AnagramSolver();
        final var anagram = new Anagram(System.out, provider, solver);
        anagram.run(args[0]);
    }
}

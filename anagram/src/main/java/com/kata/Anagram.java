package com.kata;

import java.io.PrintStream;

public class Anagram {

    private final PrintStream out;
    private final AnagramsProvider provider;
    private final AnagramSolver solver;

    public Anagram(PrintStream out, AnagramsProvider provider, AnagramSolver solver) {
        this.out = out;
        this.provider = provider;
        this.solver = solver;
    }

    public void run(String word) {
        var anagrams = provider.provide();
        solver.solve(word, anagrams).forEach(out::println);
    }
}

package com.kata;

import java.util.ArrayList;
import java.util.List;

public class Anagram {

    private final FileReader reader;
    private final AnagramSolver solver;

    public Anagram(FileReader reader) {
        this.reader = reader;
        this.solver = new AnagramSolver();
    }

    public List<List<String>> getWords(String filepath) {
        final var content = reader.read(filepath);
        final var words = content.split("\n");
        return anagramsOf(words);
    }

    private List<List<String>> anagramsOf(String[] words) {
        List<String> memoized = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        for (String word : words) {
            final var anagrams = solver.solve(word, words);
            if (memoized.stream().noneMatch(anagrams::contains)) {
                memoized.addAll(anagrams);
                result.add(anagrams);
            }
        }
        return result;
    }
}

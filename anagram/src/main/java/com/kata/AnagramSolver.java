package com.kata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnagramSolver {

    public List<String> solve(String word, List<String> anagrams) {
        if (word.isEmpty() || anagrams.isEmpty()) {
            return Collections.emptyList();
        }
        return getAnagrams(toSorted(word), anagrams);
    }

    private static List<String> getAnagrams(String word, List<String> anagrams) {
        List<String> result = new ArrayList<>();
        for (var anagram : anagrams) {
            if (word.equals(toSorted(anagram))) {
                result.add(anagram);
            }
        }
        return result;
    }

    private static String toSorted(String word) {
        return word
                .chars()
                .sorted()
                .collect(
                        StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append
                )
                .toString();
    }
}

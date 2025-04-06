package com.kata;

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
        return anagrams.stream().filter(anagram -> areAnagrams(word, anagram)).toList();
    }

    private static boolean areAnagrams(String word, String anagram) {
        return word.equals(toSorted(anagram));
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

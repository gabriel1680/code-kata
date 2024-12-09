package com.kata;

import java.util.Arrays;
import java.util.List;

public class AnagramSolver {

    public List<String> solve(String word, String[] words) {
        return Arrays.stream(words)
                .filter(aWord -> isAnagram(word, aWord))
                .toList();
    }

    private static boolean isAnagram(String a, String b) {
        if (a.length() != b.length()) return false;
        return Arrays.equals(toSortedCharArray(a), toSortedCharArray(b));
    }

    private static char[] toSortedCharArray(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return chars;
    }
}

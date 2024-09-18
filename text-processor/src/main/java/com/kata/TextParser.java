package com.kata;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class TextParser {

    public List<String> parse(String s) {
        return splitWords(s)
            .map(String::toLowerCase)
            .map(TextParser::removeNotLetters)
            .toList();
    }

    private static String removeNotLetters(String word) {
        return word.replaceAll("[^a-z]", "");
    }

    private static Stream<String> splitWords(String s) {
        return Arrays.stream(s.split(" "));
    }
}

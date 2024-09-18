package com.kata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class MostUsedWordsProcessor {

    public List<String> process(String s) {
        return s.isEmpty() ?
            emptyList() :
            sort(buildOccurrencesMap(getWords(s))).toList();
    }

    private static List<String> getWords(String s) {
        return List.of(s.split(" "));
    }

    private static HashMap<String, Integer> buildOccurrencesMap(List<String> words) {
        final var wordMap = new HashMap<String, Integer>();
        for (var word : words) {
            wordMap.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
        }
        return wordMap;
    }

    private static Stream<String> sort(HashMap<String, Integer> wordMap) {
        return wordMap.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .map(Map.Entry::getKey);
    }
}

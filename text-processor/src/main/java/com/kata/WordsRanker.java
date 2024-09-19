package com.kata;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class WordsRanker {

    public List<String> rank(List<String> words) {
        return words.isEmpty() ? emptyList() : sort(buildOccurrencesMap(words)).toList();
    }

    private static Map<String, Long> buildOccurrencesMap(List<String> words) {
        return words.stream()
            .collect(Collectors.groupingBy(w -> w, Collectors.counting()));

    }

    private static Stream<String> sort(Map<String, Long> wordMap) {
        return wordMap.entrySet().stream()
            .sorted((a, b) -> Math.toIntExact(b.getValue() - a.getValue()))
            .map(Map.Entry::getKey);
    }
}

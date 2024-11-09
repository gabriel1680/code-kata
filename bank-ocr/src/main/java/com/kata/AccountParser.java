package com.kata;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountParser {

    public String parse(String content) {
        final var accountNumberLines = List.of(content.split("\n"));
        return IntStream.iterate(0, i -> i + 3)
                .limit(9)
                .map(i -> parseOneNumberChunk(accountNumberLines, i))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining());
    }

    public int parseOneNumberChunk(List<String> lines, int n) {
        final var stringRepresentation = lines.stream()
                .map(line -> line.substring(n, n + 3))
                .collect(Collectors.joining());
        return Digit.of(stringRepresentation).value;
    }
}

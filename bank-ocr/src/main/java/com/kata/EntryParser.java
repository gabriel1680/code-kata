package com.kata;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EntryParser {

    public AccountNumber parse(String content) {
        final var accountNumberLines = getAccountLinesFrom(content);
        final var digits = getDigitsFrom(accountNumberLines);
        return new AccountNumber(digits);
    }

    private static List<String> getAccountLinesFrom(String content) {
        return List.of(content.split("\n"));
    }

    private List<Digit> getDigitsFrom(List<String> accountNumberLines) {
        return IntStream.iterate(0, i -> i + 3)
                .limit(9)
                .mapToObj(i -> toNumberString(accountNumberLines, i))
                .map(Digit::of)
                .toList();
    }

    public String toNumberString(List<String> lines, int n) {
        return lines.stream()
                .map(line -> line.substring(n, n + 3))
                .collect(Collectors.joining());
    }
}

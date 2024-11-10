package com.kata;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountParser {

    public AccountNumber parse(String content) {
        final var accountNumberLines = List.of(content.split("\n"));
        final var digits = IntStream.iterate(0, i -> i + 3)
                .limit(9)
                .mapToObj(i -> toDigit(accountNumberLines, i))
                .toList();
        return new AccountNumber(digits);
    }

    public Digit toDigit(List<String> lines, int n) {
        final var stringRepresentation = lines.stream()
                .map(line -> line.substring(n, n + 3))
                .collect(Collectors.joining());
        return Digit.of(stringRepresentation);
    }
}

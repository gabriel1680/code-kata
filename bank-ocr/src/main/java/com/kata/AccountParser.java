package com.kata;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AccountParser {

    public AccountNumber parse(String content) {
        final var accountNumberLines = List.of(content.split("\n"));
        final var digits = IntStream.iterate(0, i -> i + 3)
                .limit(9)
                .mapToObj(i -> toNumberString(accountNumberLines, i))
                .map(Digit::of)
                .toList();
        return new AccountNumber(digits);
    }

    public String toNumberString(List<String> lines, int n) {
        return lines.stream()
                .map(line -> line.substring(n, n + 3))
                .collect(Collectors.joining());
    }
}

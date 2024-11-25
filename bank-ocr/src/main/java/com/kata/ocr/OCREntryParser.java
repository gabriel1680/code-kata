package com.kata.ocr;

import com.kata.AccountNumber;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OCREntryParser {

    private final static int ENTRY_SIZE = 3;

    public AccountNumber parse(String content) {
        final var accountNumberLines = getAccountLinesFrom(content);
        final var digits = getDigitsFrom(accountNumberLines);
        return new AccountNumber(digits);
    }

    private static List<String> getAccountLinesFrom(String content) {
        return List.of(content.split("\n"));
    }

    private List<Integer> getDigitsFrom(List<String> accountNumberLines) {
        return IntStream.iterate(0, i -> i + ENTRY_SIZE)
                .limit(9)
                .mapToObj(i -> toNumberString(accountNumberLines, i))
                .map(OCRDigit::of)
                .map(digit -> digit.value)
                .toList();
    }

    public String toNumberString(List<String> lines, int n) {
        return lines.stream()
                .map(line -> line.substring(n, n + 3))
                .collect(Collectors.joining());
    }
}

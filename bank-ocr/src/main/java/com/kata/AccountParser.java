package com.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountParser {

    public String parse(String content) {
        final var accountNumbers = new ArrayList<Integer>();
        final List<String> split = List.of(content.split("\n"));
        for (int i = 0; i < 27; i += 3) {
            accountNumbers.add(parseOneNumberChunk(split, i));
        }
        return toStringAccountNumber(accountNumbers);
    }

    public int parseOneNumberChunk(List<String> lines, int n) {
        final var numberChunks = lines.stream()
                .map(line -> line.substring(n, n + 3))
                .toList();
        return numberChunksToInt(numberChunks);
    }

    private static int numberChunksToInt(List<String> list) {
        final var stringNumber = String.join("", list);
        return Digit.of(stringNumber).value;
    }

    private static String toStringAccountNumber(ArrayList<Integer> result) {
        return result.stream().map(String::valueOf).collect(Collectors.joining());
    }
}

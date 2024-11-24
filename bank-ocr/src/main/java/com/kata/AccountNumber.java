package com.kata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

public record AccountNumber(List<Integer> digits) {

    public boolean checksum() {
        return calculateChecksum(digits);
    }

    private boolean calculateChecksum(List<Integer> digits) {
        final int checksum = IntStream
                .range(0, digits.size())
                .map(i -> digits.get(i) * (i - digits.size()))
                .sum();
        return checksum % 11 == 0;
    }

    public List<String> getPossibleCombinations() {
        if (checksum()) {
            return singletonList(digitsToString(digits));
        }
        return removeDuplicationsFor(digits).stream()
                .map(AccountNumber::getProximalOf)
                .flatMap(Collection::stream)
                .map(this::getOneDigitsCombinations)
                .flatMap(Collection::stream)
                .toList();
    }

    private List<Integer> removeDuplicationsFor(List<Integer> aList) {
        return new ArrayList<>(new HashSet<>(aList));
    }

    private List<String> getOneDigitsCombinations(int element) {
        final List<String> result = new ArrayList<>();
        for (int i = 0; i < digits.size(); i++) {
            var mutableDigits = new ArrayList<>(digits);
            mutableDigits.set(i, element);
            if (calculateChecksum(mutableDigits))
                result.add(digitsToString(mutableDigits));
        }
        return result;
    }

    private String digitsToString(List<Integer> list) {
        return list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    private static List<Integer> getProximalOf(int n) {
        return switch (n) {
            case 0 -> List.of(8);
            case 1 -> List.of(7);
            case 3 -> List.of(9);
            case 5 -> List.of(6, 9);
            case 6 -> List.of(5, 8);
            case 7 -> List.of(1);
            case 8 -> List.of(6, 0, 9);
            case 9 -> List.of(8, 3, 5);
            default -> emptyList();
        };
    }
}

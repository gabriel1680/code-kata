package com.kata;

import java.util.List;
import java.util.stream.IntStream;

public record AccountNumber(List<Integer> digits) {

    public boolean checksum() {
        final int checksum = IntStream
                .range(0, digits.size())
                .map(i -> digits.get(i) * (i - digits.size()))
                .sum();
        return checksum % 11 == 0;
    }
}

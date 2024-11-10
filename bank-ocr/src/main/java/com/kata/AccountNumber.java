package com.kata;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class AccountNumber {

    private final List<Integer> digits;

    public AccountNumber(List<Integer> digits) {
        this.digits = unmodifiableList(digits);
    }

    @Override
    public String toString() {
        return digits.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public boolean checksum() {
        int result = 0;
        for (int i = 0; i < digits.size(); i++) {
            result += digits.get(i) * (i - digits.size());
        }
        return result % 11 == 0;
    }

    public List<Integer> getDigits() {
        return digits;
    }
}

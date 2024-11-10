package com.kata;

import java.util.List;
import java.util.stream.Collectors;

public class AccountNumber {

    private final List<Integer> digits;

    public AccountNumber(List<Digit> digits) {
        this.digits = digits.stream().map(digit -> digit.value).toList();
    }

    @Override
    public String toString() {
        return digits.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

    public boolean checksum() {
        int result = 0;
        for (int i = 9; i > 0; i--) {
            result += digits.get(i - 1) * i;
        }
        return result % 11 == 0;
    }
}

package com.kata;

import java.util.List;
import java.util.stream.Collectors;

public class AccountNumber {
    private final List<Digit> digits;

    public AccountNumber(List<Digit> digits) {
        this.digits = digits;
    }

    @Override
    public String toString() {
        return digits.stream()
                .map(digit -> digit.value)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}

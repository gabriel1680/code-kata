package com.kata;

import java.util.List;

public record AccountNumber(List<Integer> digits) {

    public boolean checksum() {
        int result = 0;
        for (int i = 0; i < digits.size(); i++) {
            result += digits.get(i) * (i - digits.size());
        }
        return result % 11 == 0;
    }
}

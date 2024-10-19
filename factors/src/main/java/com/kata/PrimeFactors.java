package com.kata;

import java.util.ArrayList;
import java.util.List;

public class Factors {

    public List<Integer> of(int n) {
        final var result = new ArrayList<Integer>();
        for (int divisor = 2; n > 1; divisor++) {
            for (; n % divisor == 0; n /= divisor) {
                result.add(divisor);
            }
        }
        return result;
    }
}

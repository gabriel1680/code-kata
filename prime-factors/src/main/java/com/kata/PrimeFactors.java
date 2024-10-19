package com.kata;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public List<Integer> of(int n) {
        final var result = new ArrayList<Integer>();
        if (n > 1) {
            result.add(n);
        }
        return result;
    }
}

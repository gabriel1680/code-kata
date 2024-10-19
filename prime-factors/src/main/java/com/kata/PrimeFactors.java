package com.kata;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public List<Integer> of(int n) {
        final var result = new ArrayList<Integer>();
        if (n > 1) {
            while (n % 2 == 0) {
                result.add(2);
                n /= 2;
            }
            while (n % 3 == 0) {
                result.add(3);
                n /= 3;
            }
        }
        if (n > 1) {
            result.add(n);
        }
        return result;
    }
}

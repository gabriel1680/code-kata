package com.kata;

import java.util.ArrayList;
import java.util.List;

public class PrimeFactors {
    public List<Integer> of(int n) {
        final var result = new ArrayList<Integer>();
        var divisor = 2;
        while (n > 1) {
            while (n % divisor == 0) {
                result.add(divisor);
                n /= divisor;
            }
            divisor++;
        }
        if (n > 1) {
            result.add(n);
        }
        return result;
    }
}

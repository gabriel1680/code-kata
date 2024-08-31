package com.wordtyping;

import java.util.ArrayList;
import java.util.List;

public class WardrobePossibilities {
    private final List<Integer> availableMeasures;

    public WardrobePossibilities(List<Integer> someMeasures) {
        availableMeasures = someMeasures;
    }

    public List<List<Integer>> getPossibilitiesFor(final int size) {
        List<List<Integer>> result = new ArrayList<>();
        for (var i = 0; i < availableMeasures.size(); i++) {
            final var availableMeasure = availableMeasures.get(i);
            iterateForSubValues(size, i, availableMeasure, result);
        }
        return result;
    }

    private void iterateForSubValues(int size, int i, Integer availableMeasure, List<List<Integer>> result) {
        for (var j = i; j >= 0; j--) {
            final var actual = new ArrayList<Integer>();
            if (tryReduceToZero(availableMeasure, size, j, actual) == 0) {
                result.add(actual);
            }
        }
    }

    private int tryReduceToZero(Integer availableMeasure, int value, int j, ArrayList<Integer> actual) {
        while (value > 0) {
            if (j == 0) {
                actual.add(availableMeasure);
                value -= availableMeasure;
            } else if ((value - availableMeasure) > availableMeasures.get(j - 1)) {
                actual.add(availableMeasure);
                value -= availableMeasure;
            } else {
                var prev = availableMeasures.get(j - 1);
                actual.add(prev);
                value -= prev;
            }
        }
        return value;
    }
}

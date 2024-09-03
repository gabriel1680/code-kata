package com.wordtyping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Wardrobe {
    private final int[] availableMeasures;
    private final Map<Integer, Double> measures;

    public Wardrobe(Map<Integer, Double> someMeasures) {
        availableMeasures = toArray(someMeasures);
        measures = someMeasures;
    }

    private static int[] toArray(Map<Integer, Double> someMeasures) {
        return someMeasures.keySet().stream().mapToInt(i -> i).sorted().toArray();
    }

    public List<List<Integer>> getCombinationsFor(final int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(0, target, result, new ArrayList<>());
        return result;
    }

    private void findCombinations(int idx, int target, List<List<Integer>> result, List<Integer> ds) {
        if (target == 0) {
            result.add(new ArrayList<>(ds));
            return;
        }
        if (idx >= availableMeasures.length || target < 0) {
            return;
        }
        ds.add(availableMeasures[idx]);
        findCombinations(idx, target - availableMeasures[idx], result, ds);
        ds.remove(ds.size() - 1);
        findCombinations(idx + 1, target, result, ds);
    }

    public List<Integer> getCheapestFor(final int size) {
        final List<Number> result = Arrays.asList(0, Double.POSITIVE_INFINITY);
        final var combinations = getCombinationsFor(size);
        for (int i = 0; i < combinations.size(); i++)
            findCheapestOf(combinations.get(i), i, result);
        return combinations.get((Integer) result.get(0));
    }

    private void findCheapestOf(List<Integer> combination, int i, List<Number> result) {
        final var acc = reduce(combination);
        if (((Double) result.get(1)).compareTo(acc) > 0) {
            result.set(0, i);
            result.set(1, acc);
        }
    }

    private Double reduce(List<Integer> combination) {
        Double acc = 0.0;
        for (var value : combination)
            acc += measures.get(value);
        return acc;
    }
}

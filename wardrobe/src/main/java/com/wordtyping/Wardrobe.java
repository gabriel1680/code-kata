package com.wordtyping;

import java.util.ArrayList;
import java.util.List;

public class Wardrobe {
    private final int[] availableMeasures;

    public Wardrobe(List<Integer> someMeasures) {
        availableMeasures = someMeasures.stream().mapToInt(Integer::intValue).toArray();
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
}

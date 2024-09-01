package com.wordtyping;

import java.util.ArrayList;
import java.util.List;

public class WardrobePossibilities {
    private final int[] availableMeasures;

    public WardrobePossibilities(List<Integer> someMeasures) {
        availableMeasures = someMeasures.stream().mapToInt(Integer::intValue).toArray();
    }

    public List<List<Integer>> getPossibilitiesFor(final int target) {
        List<List<Integer>> result = new ArrayList<>();
        findCombinations(0, target, result, new ArrayList<>());
        return result;
    }

    private void findCombinations(int idx, int target, List<List<Integer>> result, List<Integer> ds) {
        if (idx == availableMeasures.length) {
            if (target == 0) result.add(new ArrayList<>(ds));
            return;
        }
        if (availableMeasures[idx] <= target) {
            ds.add(availableMeasures[idx]);
            findCombinations(idx, target - availableMeasures[idx], result, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinations(idx + 1, target, result, ds);
    }
}

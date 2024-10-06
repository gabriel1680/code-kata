package com.kata;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Yatzy {

    public int chance(List<Integer> dices) {
        return sum(dices);
    }

    private static int sum(List<Integer> dices) {
        return dices.stream().mapToInt(Integer::intValue).sum();
    }

    public int yatzy(List<Integer> dices) {
        return dices.stream().allMatch(dices.get(0)::equals) ? 50 : 0;
    }

    public int pair(List<Integer> dices) {
        final var dicesOccurenceMap = dices.stream()
            .collect(groupingBy(Integer::intValue, counting()));
        final var greaterKeyPair = dicesOccurenceMap.entrySet().stream()
            .filter(entry -> entry.getValue() == 2)
            .map(Map.Entry::getKey)
            .max(Comparator.naturalOrder());
        return greaterKeyPair.orElse(0) * 2;
    }

    public int smallStraight(List<Integer> dices) {
        final var smallStraightDices = List.of(1, 2, 3, 4, 5);
        return getStraightOf(dices, smallStraightDices);
    }

    public int largeStraight(List<Integer> dices) {
        final var largeStraightDices = List.of(2, 3, 4, 5, 6);
        return getStraightOf(dices, largeStraightDices);
    }

    private static int getStraightOf(List<Integer> dices, List<Integer> sequence) {
        return dices.equals(sequence) ? sum(dices) : 0;
    }
}

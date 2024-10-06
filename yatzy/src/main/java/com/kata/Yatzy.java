package com.kata;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

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
        final var greaterKeyPair = getListOfDicesOccurred(occurrencesMapOf(dices), 2)
            .max(Comparator.naturalOrder());
        return greaterKeyPair.orElse(0) * 2;
    }

    private static Stream<Integer> getListOfDicesOccurred(Map<Integer, Long> dicesOccurenceMap, int i) {
        return dicesOccurenceMap.entrySet().stream()
                .filter(entry -> entry.getValue() == i)
                .map(Map.Entry::getKey);
    }

    private static Map<Integer, Long> occurrencesMapOf(List<Integer> dices) {
        return dices.stream()
            .collect(groupingBy(Integer::intValue, counting()));
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

    public int fullHouse(List<Integer> dices) {
        final var dicesOccurenceMap = occurrencesMapOf(dices);
        final int twoOfAKind = getDiceWithOccurrences(dicesOccurenceMap, 2).orElse(0);
        final int threeOfAKind = getDiceWithOccurrences(dicesOccurenceMap, 3).orElse(0);
        return isFullHouse(twoOfAKind, threeOfAKind) ? (twoOfAKind * 2) + (threeOfAKind * 3) : 0;
    }

    private static Optional<Integer> getDiceWithOccurrences(
        Map<Integer, Long> dicesOccurenceMap,
        int i
    ) {
        return getListOfDicesOccurred(dicesOccurenceMap, i).findFirst();
    }

    private static boolean isFullHouse(int twoOfAKind, int threeOfAKind) {
        return twoOfAKind != 0 && threeOfAKind != 0;
    }
}

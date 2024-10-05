package com.kata;

import java.util.List;

public class Yatzy {

    public int chance(List<Integer> rolls) {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }

    public int yatzy(List<Integer> dices) {
        return dices.stream().allMatch(dices.get(0)::equals) ? 50 : 0;
    }
}

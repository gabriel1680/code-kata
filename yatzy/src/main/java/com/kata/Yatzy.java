package com.kata;

import java.util.List;

public class Yatzy {

    public int chance(List<Integer> rolls) {
        return rolls.stream().mapToInt(Integer::intValue).sum();
    }
}

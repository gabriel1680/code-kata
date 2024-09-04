package com.example;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class CoinChanger {
    public List<Integer> exchange(final int amount) {
        final var result = new ArrayList<Integer>();
        var counter = amount;
        for (Coin coin : Coin.values()) {
            final var acc = reduceCoin(coin, counter);
            result.add(acc);
            counter -= acc * coin.value;
        }
        return unmodifiableList(result);
    }

    private static int reduceCoin(Coin coin, int counter) {
        int acc = 0;
        while (counter >= coin.value) {
            acc++;
            counter -= coin.value;
        }
        return acc;
    }
}

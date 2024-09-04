package com.example;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class CoinChanger {
    public List<Integer> exchange(final int amount) {
        final var result = new ArrayList<Integer>();
        var counter = amount;
        for (Coin coin : Coin.values()) {
            int currentCoinAcc = 0;
            while (counter >= coin.value) {
                currentCoinAcc++;
                counter -= coin.value;
            }
            result.add(currentCoinAcc);
        }
        return unmodifiableList(result);
    }
}

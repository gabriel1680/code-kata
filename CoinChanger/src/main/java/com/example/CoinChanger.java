package com.example;

public class CoinChanger {
    public int[] exchange(int amount) {
        final var result = new int[]{0, 0, 0, 0, 0};
        var counter = amount;
        while (counter > 0) {
            counter = calculate(counter, result);
        }
        return result;
    }

    private static int calculate(int counter, int[] result) {
        if (counter >= 100) {
            result[4]++;
            counter -= 100;
        } else if (counter >= 25) {
            result[3]++;
            counter -= 25;
        } else if (counter >= 10) {
            result[2]++;
            counter -= 10;
        } else if (counter >= 5) {
            result[1]++;
            counter -= 5;
        } else {
            result[0]++;
            counter -= 1;
        }
        return counter;
    }
}

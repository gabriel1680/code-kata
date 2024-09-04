package com.example;

public enum Coin {

    QUARTERS(25),
    DIMES(10),
    NICKLES(5),
    PENNIES(1);

    public final int value;

    Coin(final int aValue) {
        value = aValue;
    }
}

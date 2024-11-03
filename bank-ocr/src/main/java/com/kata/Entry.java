package com.kata;

import java.util.Arrays;

public enum Entry {
    ZERO("_\n| |\n|_|\n", 0),
    ONE("\n|\n|\n", 1),
    TWO("_\n_|\n|_\n", 2),
    THREE("_\n_|\n_|\n", 3),
    FOUR("\n|_|\n|\n", 4),
    FIVE("_\n|_\n_|\n", 5),
    SIX("_\n|_\n|_|\n", 6),
    SEVEN("_\n|\n|\n", 7),
    EIGHT("_\n|_|\n|_|\n", 8),
    NINE("_\n|_|\n_|\n", 9);

    public final int value;
    public final String representation;

    Entry(String representation, int value) {
        this.value = value;
        this.representation = representation;
    }

    public static Entry of(String s) {
        return Arrays.stream(Entry.values())
                .filter(entry -> entry.representation.equals(s))
                .findFirst()
                .orElse(ZERO);
    }
}

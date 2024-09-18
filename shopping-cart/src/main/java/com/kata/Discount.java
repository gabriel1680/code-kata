package com.kata;

public record Discount(String name, double percentage) {

    public double applyFor(double total) {
        return (1 - percentage) * total;
    }

    public static Discount empty() {
        return new Discount("", 0.00);
    }
}

package com.kata;

public class Year {

    private final long year;

    public Year(long year) {
        this.year = year;
    }

    public boolean isLeap() {
        return isDivisibleBy(4) && !(isDivisibleBy(100) && !isDivisibleBy(400));
    }

    private boolean isDivisibleBy(int number) {
        return year % number == 0;
    }
}

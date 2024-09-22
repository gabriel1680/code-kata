package com.kata;

public class Year {

    public boolean isLeap(long year) {
        return year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0);
    }
}

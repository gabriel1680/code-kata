package com.kata;

public class Year {

    public boolean isLeap(long year) {
        if (year % 4 == 0 && year % 100 == 0 && year % 400 != 0)
            return false;
        else if (year % 4 == 0)
            return true;
        else return year % 400 == 0;
    }
}

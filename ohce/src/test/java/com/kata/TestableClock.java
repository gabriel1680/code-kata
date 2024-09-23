package com.kata;

public class TestableClock implements Clock {
    private int hour = 0;

    @Override
    public int getHour() {
        return hour;
    }

    public void setHour(int aHour) {
        hour = aHour;
    }
}

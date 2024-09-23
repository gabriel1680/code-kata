package com.kata;

import java.time.LocalDateTime;

public class SystemClock implements Clock {

    @Override
    public int getHour() {
        return LocalDateTime.now().getHour();
    }
}

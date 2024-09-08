package com.kata;

public class MyMorningRoutine implements MorningRoutine {

    private final Clock clock;

    public MyMorningRoutine(Clock aClock) {
        clock = aClock;
    }

    @Override
    public String whatShouldIDoNow() {
        final var currentHour = clock.getHour();
        if (currentHour == 6) {
            return "Do Exercise";
        } else if (currentHour == 7) {
            return "Read and study";
        } else if (currentHour == 8) {
            return "Have breakfast";
        } else {
            return "No activity";
        }
    }
}

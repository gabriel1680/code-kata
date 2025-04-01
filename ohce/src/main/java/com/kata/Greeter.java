package com.kata;

public class Greeter {

    private final Clock clock;

    public Greeter(Clock clock) {
        this.clock = clock;
    }

    public String greet(String name) {
        return getCurrentTimeGreet() + name + "!";
    }

    public String getCurrentTimeGreet() {
        int hour = clock.getHour();
        if (isMorning(hour))
            return "Buenos días";
        else if (isAfternoon(hour))
            return "Buenas tardes";
        else
            return "Buenas noches";
    }

    private static boolean isAfternoon(int hour) {
        return isBetween(hour, 12, 20);
    }

    private static boolean isMorning(int hour) {
        return isBetween(hour, 6, 12);
    }

    private static boolean isBetween(int hour, int start, int end) {
        return hour >= start && hour < end;
    }
}

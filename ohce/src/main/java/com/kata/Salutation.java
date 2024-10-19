package com.kata;

public class Salutation {

    public String at(int hour) {
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

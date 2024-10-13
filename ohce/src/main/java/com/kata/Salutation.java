package com.kata;

public class Salutation {

    public String at(int hour) {
        if (isNight(hour))
            return "Buenas noches";
        else if (isMorning(hour))
            return "Buenos días";
        else
            return "Buenas tardes";
    }

    private static boolean isNight(int hour) {
        return hour >= 20 || hour < 6;
    }

    private static boolean isMorning(int hour) {
        return hour >= 6 && hour < 12;
    }
}

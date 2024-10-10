package com.kata;

public class Rover {

    private String position;

    public Rover(String aPosition) {
        position = aPosition;
    }

    public Rover() {
        position = "N";
    }

    public String getPosition() {
        return "0:0:" + position;
    }

    public void move(String commands) {
        for (final var command : commands.toCharArray()) {
            moveRight();
        }
    }

    private void moveRight() {
        if (position == "N")
            position = "E";
        else if (position == "E")
            position = "S";
        else if (position == "S")
            position = "W";
        else
            position = "N";
    }
}

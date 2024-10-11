package com.kata;

public class Rover {

    private String position;
    private int x;
    private int y;

    public Rover(int y, String position) {
        this.y = y;
        this.position = position;
    }

    public Rover(String aPosition) {
        position = aPosition;
        y = 0;
        x = 0;
    }

    public Rover() {
        position = "N";
        y = 0;
        x = 0;
    }

    public Rover(int x, int y, String position) {
        this.x = x;
        this.y = y;
        this.position = position;
    }

    public String getPosition() {
        return x + ":" + y + ":" + position;
    }

    public void move(String commands) {
        for (final var command : commands.toCharArray()) {
            if (command == 'R') {
                turnRight();
            } else if (command == 'L') {
                turnLeft();
            } else {
                moveForward();
            }
        }
    }

    private void moveForward() {
        if (position == "N") y += 1;
        else if (position == "S") y -= 1;
        else if (position == "W") x -= 1;
        else x += 1;
    }

    private void turnLeft() {
        if (position == "N") position = "W";
        else if (position == "W") position = "S";
        else if (position == "S") position = "E";
        else position = "N";
    }

    private void turnRight() {
        if (position == "N") position = "E";
        else if (position == "E") position = "S";
        else if (position == "S") position = "W";
        else position = "N";
    }
}

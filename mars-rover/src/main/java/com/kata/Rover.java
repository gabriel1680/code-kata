package com.kata;

public class Rover {

    private Direction direction;
    private int x;
    private int y;

    public Rover(int y, String position) {
        this.y = y;
        this.direction = Direction.valueOf(position);
    }

    public Rover(String aPosition) {
        this.direction = Direction.valueOf(aPosition);
        y = 0;
        x = 0;
    }

    public Rover() {
        this.direction = Direction.N;
        y = 0;
        x = 0;
    }

    public Rover(int x, int y, String position) {
        this.x = x;
        this.y = y;
        this.direction = Direction.valueOf(position);
    }

    public String getPosition() {
        return x + ":" + y + ":" + direction.value;
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
        if (direction.value == "N") y += 1;
        else if (direction.value == "S") y -= 1;
        else if (direction.value == "W") x -= 1;
        else x += 1;
    }

    private void turnLeft() {
        if (direction.value == "N") {
            direction = Direction.W;
        }
        else if (direction.value == "W") {
            direction = Direction.S;
        }
        else if (direction.value == "S") {
            direction = Direction.E;
        }
        else {
            direction = Direction.N;
        }
    }

    private void turnRight() {
        if (direction.value == "N") {
            direction = Direction.E;
        }
        else if (direction.value == "E") {
            direction = Direction.S;
        }
        else if (direction.value == "S") {
            direction = Direction.W;
        }
        else {
            direction = Direction.N;
        }
    }

    public enum Direction {
        N("N", "W", "E"),
        E("E", "N", "S"),
        W("W", "S", "N"),
        S("S", "E", "W");

        public final String value;
        public final String left;
        public final String right;

        Direction(String value, String left, String right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}

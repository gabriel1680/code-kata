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
                direction = direction.turnRight();
            } else if (command == 'L') {
                direction = direction.turnLeft();
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

        public Direction turnLeft() {
            return Direction.valueOf(left);
        }

        public Direction turnRight() {
            return Direction.valueOf(right);
        }
    }
}

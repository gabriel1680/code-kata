package com.kata;

public class Rover {

    private Direction direction;
    private Coordinate coordinate;

    public Rover(int y, String position) {
        this.direction = Direction.valueOf(position);
        coordinate = new Coordinate(0, y);
    }

    public Rover(String aPosition) {
        this.direction = Direction.valueOf(aPosition);
        coordinate = new Coordinate(0, 0);
    }

    public Rover() {
        this.direction = Direction.N;
        coordinate = new Coordinate(0, 0);
    }

    public Rover(int x, int y, String position) {
        this.direction = Direction.valueOf(position);
        coordinate = new Coordinate(x, y);
    }

    public String getPosition() {
        return coordinate.x + ":" + coordinate.y + ":" + direction.value;
    }

    public void move(String commands) {
        for (final var command : commands.toCharArray()) {
            if (command == 'R') {
                direction = direction.turnRight();
            } else if (command == 'L') {
                direction = direction.turnLeft();
            } else {
                coordinate = coordinate.moveForward(direction);
            }
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

        public Direction turnLeft() {
            return Direction.valueOf(left);
        }

        public Direction turnRight() {
            return Direction.valueOf(right);
        }
    }
}

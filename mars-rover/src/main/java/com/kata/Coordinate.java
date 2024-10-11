package com.kata;

public record Coordinate(int x, int y) {

    public static final Coordinate origin = new Coordinate(0, 0);

    public Coordinate moveForwardTo(Direction aDirection) {
        return switch (aDirection) {
            case N -> new Coordinate(x, y + 1);
            case S -> new Coordinate(x, y - 1);
            case E -> new Coordinate(x + 1, y);
            case W -> new Coordinate(x - 1, y);
        };
    }
}

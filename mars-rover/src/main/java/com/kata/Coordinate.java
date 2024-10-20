package com.kata;

public record Coordinate(int x, int y) {

    public static final Coordinate origin = new Coordinate(0, 0);

    public Coordinate moveForwardTo(Direction aDirection) {
        return switch (aDirection) {
            case N -> new Coordinate(x, shift(y));
            case S -> new Coordinate(x, unshift(y));
            case E -> new Coordinate(shift(x), y);
            case W -> new Coordinate(unshift(x), y);
        };
    }

    public Coordinate moveBackwardTo(Direction aDirection) {
        return switch (aDirection) {
            case N -> new Coordinate(x, unshift(y));
            case S -> new Coordinate(x, shift(y));
            case E -> new Coordinate(unshift(x), y);
            case W -> new Coordinate(shift(x), y);
        };
    }

    private int shift(int coord) {
        return (coord + 1) % 11;
    }

    private int unshift(int coord) {
        return coord > 0 ? coord - 1 : 10;
    }
}

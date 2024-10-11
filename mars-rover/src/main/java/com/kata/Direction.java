package com.kata;

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
package com.kata;

public class Position {
    private Direction direction;
    private Coordinate coordinate;

    public Position(int x, int y, String direction) {
        this.direction = Direction.valueOf(direction);
        this.coordinate = new Coordinate(x, y);
    }

    public Position() {
        coordinate = Coordinate.origin;
        direction = Direction.N;
    }

    public void turnRight() {
        direction = direction.turnRight();
    }

    public void turnLeft() {
        direction = direction.turnLeft();
    }

    public void moveForward() {
        coordinate = coordinate.moveForwardTo(direction);
    }

    public void moveBackwards() {
        coordinate = coordinate.moveBackwardTo(direction);
    }

    public Direction direction() {
        return direction;
    }

    public Coordinate coordinate() {
        return coordinate;
    }
}

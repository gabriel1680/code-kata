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

    private Position(Coordinate coordinate, Direction direction) {
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public Position(Direction direction) {
        this.direction = direction;
    }

    public Position turnRight() {
        return new Position(coordinate, direction.turnRight());
    }

    public Position turnLeft() {
        return new Position(coordinate, direction.turnLeft());
    }

    public Position moveForward() {
        return new Position(coordinate.moveForwardTo(direction), direction);
    }

    public Position moveBackwards() {
        return new Position(coordinate.moveBackwardTo(direction), direction);
    }

    public Direction direction() {
        return direction;
    }

    public Coordinate coordinate() {
        return coordinate;
    }
}

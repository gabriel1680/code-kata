package com.kata;

import java.util.List;

public class PlanetGrid {

    private static final int MAX_EDGE_SIZE = 10;

    private final List<Coordinate> obstacles;

    public PlanetGrid(List<Coordinate> someObstacles) {
        this.obstacles = someObstacles;
    }

    public boolean haveCollisionOn(Position newPosition) {
        return obstacles.contains(newPosition.coordinate());
    }

    public boolean passedThroughtEdged(Position position) {
        return passedOnEdgeFrom(position.coordinate().x()) || passedOnEdgeFrom(position.coordinate().y());
    }

    private static boolean passedOnEdgeFrom(int coordinate) {
        return coordinate < 0 || coordinate > 10;
    }

    public Position wrapEdgesOf(Position position) {
        final var coordinate = position.coordinate();
        final var x = coordinate.x();
        final var y = coordinate.y();
        final var direction = position.direction().toString();
        if (x > 10) {
            return new Position(shift(x), y, direction);
        } else if (y > 10) {
            return new Position(x, shift(y), direction);
        } else if (x < 0) {
            return new Position(MAX_EDGE_SIZE, y, direction);
        } else if (y < 0) {
            return new Position(x, MAX_EDGE_SIZE, direction);
        } else {
            return position;
        }
    }

    private int shift(int coordinate) {
        return coordinate % 11;
    }
}

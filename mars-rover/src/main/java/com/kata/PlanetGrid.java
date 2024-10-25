package com.kata;

import java.util.List;

public class PlanetGrid {

    private static final int MIN_EDGE_SIZE = 0;
    private static final int MAX_EDGE_SIZE = 10;

    private final List<Coordinate> obstacles;

    public PlanetGrid(List<Coordinate> someObstacles) {
        this.obstacles = someObstacles;
    }

    public boolean haveCollisionOn(Position newPosition) {
        return obstacles.contains(newPosition.coordinate());
    }

    public boolean passedThroughEdged(Position position) {
        return passedOnEdgeFrom(position.coordinate().x()) || passedOnEdgeFrom(position.coordinate().y());
    }

    private static boolean passedOnEdgeFrom(int coordinate) {
        return coordinate < MIN_EDGE_SIZE || coordinate > MAX_EDGE_SIZE;
    }

    public Position wrapEdgesOf(Position position) {
        final var coordinate = position.coordinate();
        final var x = coordinate.x();
        final var y = coordinate.y();
        final var direction = position.direction().toString();
        if (isBeyondMaxEdge(x)) {
            return new Position(shift(x), y, direction);
        } else if (isBeyondMaxEdge(y)) {
            return new Position(x, shift(y), direction);
        } else if (isBeyondMinEdge(x)) {
            return new Position(MAX_EDGE_SIZE, y, direction);
        } else if (isBeyondMinEdge(y)) {
            return new Position(x, MAX_EDGE_SIZE, direction);
        } else {
            return position;
        }
    }

    private static boolean isBeyondMaxEdge(int position) {
        return position > MAX_EDGE_SIZE;
    }

    private static boolean isBeyondMinEdge(int position) {
        return position < MIN_EDGE_SIZE;
    }

    private int shift(int coordinate) {
        return coordinate % (MAX_EDGE_SIZE + 1);
    }
}

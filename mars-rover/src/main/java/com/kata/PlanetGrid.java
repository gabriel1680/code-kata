package com.kata;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class PlanetGrid {

    private final List<Coordinate> obstacles;

    public PlanetGrid(List<Coordinate> someObstacles) {
        this.obstacles = someObstacles;
    }

    public List<Coordinate> obstacles() {
        return unmodifiableList(obstacles);
    }

    public boolean haveCollisionOn(Position newPosition) {
        return obstacles.contains(newPosition.coordinate());
    }
}

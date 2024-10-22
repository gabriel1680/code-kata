package com.kata;

import static java.util.Collections.emptyList;

public class Rover {

    private Position position;
    private final PlanetGrid grid;

    public Rover(PlanetGrid aGrid) {
        this.grid = aGrid;
        this.position = new Position();
    }

    public Rover() {
        this.grid = new PlanetGrid(emptyList());
        this.position = new Position();
    }

    private Rover(Position aPosition) {
        this.position = aPosition;
        this.grid = new PlanetGrid(emptyList());
    }

    public static Rover at(int x, int y, String direction) {
        return new Rover(new Position(x, y, direction));
    }

    public void move(String commands) {
        for (final var command : commands.toCharArray())
            execute(command);
    }

    private void execute(char command) {
        final var newPosition = getNewPositionOf(command);
        if (grid.haveCollisionOn(newPosition)) {
            return;
        }
        if (grid.passedThroughtEdged(newPosition)) {
            position = grid.wrapEdgesOf(newPosition);
        } else {
            position = newPosition;
        }
    }

    private Position getNewPositionOf(char command) {
        if (command == 'R') {
            return position.turnRight();
        } else if (command == 'L') {
            return position.turnLeft();
        } else if (command == 'F') {
            return position.moveForward();
        } else if (command == 'B') {
            return position.moveBackwards();
        } else {
            return position;
        }
    }

    public String getPosition() {
        return position.coordinate().x() + ":" + position.coordinate().y() + ":" + position.direction().value;
    }
}

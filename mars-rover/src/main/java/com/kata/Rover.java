package com.kata;

public class Rover {

    private final Position position;

    public Rover(int y, String position) {
        this.position = new Position(0, y, position);
    }

    public Rover(String aPosition) {
        this.position = new Position(0, 0, aPosition);
    }

    public Rover() {
        this.position = new Position(0, 0, "N");
    }

    public Rover(int x, int y, String position) {
        this.position = new Position(x, y, position);
    }

    public String getPosition() {
        return position.coordinate().x() + ":" + position.coordinate().y() + ":" + position.direction().value;
    }

    public void move(String commands) {
        for (final var command : commands.toCharArray()) {
            if (command == 'R') {
                position.turnRight();
            } else if (command == 'L') {
                position.turnLeft();
            } else {
                position.moveForward();
            }
        }
    }
}

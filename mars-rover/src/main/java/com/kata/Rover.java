package com.kata;

public class Rover {

    private final Position position;

    public Rover() {
        this.position = new Position();
    }

    private Rover(Position aPosition) {
        this.position = aPosition;
    }

    public static Rover at(int x, int y, String direction) {
        return new Rover(new Position(x, y, direction));
    }

    public static Rover facing(String direction) {
        return new Rover(new Position(0, 0, direction));
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

    public String getPosition() {
        return position.toString();
    }
}

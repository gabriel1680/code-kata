package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover();
    }

    @Test
    void initialPositionOn00N() {
        assertEquals("0:0:N", rover.getPosition());
    }

    @Test
    void startFacingNorth_whenTurnRight_shouldBeFacingEast() {
        rover.move("R");
        assertEquals("0:0:E", rover.getPosition());
    }

    @Test
    void startFacingEast_whenTurnRight_shouldBeFacingSouth() {
        rover = new Rover("E");
        rover.move("R");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void startFacingSouth_whenTurnRight_shouldBeFacingWest() {
        rover = new Rover("S");
        rover.move("R");
        assertEquals("0:0:W", rover.getPosition());
    }

    @Test
    void startFacingWest_whenTurnRight_shouldBeFacingNorth() {
        rover = new Rover("W");
        rover.move("R");
        assertEquals("0:0:N", rover.getPosition());
    }

    @Test
    void whenMoveRightTwoTimesShouldBeFacingSouth() {
        rover.move("RR");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void whenMoveRightFourTimesShouldBeFacingNorthAgain() {
        rover.move("RRRR");
        assertEquals("0:0:N", rover.getPosition());
    }

    @Test
    void startFacingNorth_whenTurnLeft_shouldBeFacingWest() {
        rover.move("L");
        assertEquals("0:0:W", rover.getPosition());
    }

    @Test
    void startFacingWest_whenTurnLeft_shouldBeFacingSouth() {
        rover = new Rover("W");
        rover.move("L");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void startFacingSouth_whenTurnLeft_shouldBeFacingEast() {
        rover = new Rover("S");
        rover.move("L");
        assertEquals("0:0:E", rover.getPosition());
    }

    @Test
    void startFacingEast_whenTurnLeft_shouldBeFacingNorth() {
        rover = new Rover("E");
        rover.move("L");
        assertEquals("0:0:N", rover.getPosition());
    }
}
package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RoverTest {

    private Rover rover;

    @BeforeEach
    void setUp() {
        rover = new Rover();
    }

    private void givenARoverFacing(String direction) {
        rover = Rover.at(0, 0, direction);
    }

    @Test
    void initialPositionOn00N() {
        assertEquals("0:0:N", rover.getPosition());
    }

    @Test
    void ignoreOnInvalidCommand() {
        rover.move("X");
        assertEquals("0:0:N", rover.getPosition());
    }

    @Test
    void startFacingNorth_whenTurnRight_shouldBeFacingEast() {
        rover.move("R");
        assertEquals("0:0:E", rover.getPosition());
    }

    @Test
    void startFacingEast_whenTurnRight_shouldBeFacingSouth() {
        givenARoverFacing("E");
        rover.move("R");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void startFacingSouth_whenTurnRight_shouldBeFacingWest() {
        givenARoverFacing("S");
        rover.move("R");
        assertEquals("0:0:W", rover.getPosition());
    }

    @Test
    void startFacingWest_whenTurnRight_shouldBeFacingNorth() {
        givenARoverFacing("W");
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
        givenARoverFacing("W");
        rover.move("L");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void startFacingSouth_whenTurnLeft_shouldBeFacingEast() {
        givenARoverFacing("S");
        rover.move("L");
        assertEquals("0:0:E", rover.getPosition());
    }

    @Test
    void startFacingEast_whenTurnLeft_shouldBeFacingNorth() {
        givenARoverFacing("E");
        rover.move("L");
        assertEquals("0:0:N", rover.getPosition());
    }

    private void givenARoverAt(int x, int y, String direction) {
        rover = Rover.at(x, y, direction);
    }

    @Test
    void startOn00North_whenMoveForward_shouldBeAt01() {
        rover.move("F");
        assertEquals("0:1:N", rover.getPosition());
        rover.move("F");
        assertEquals("0:2:N", rover.getPosition());
    }

    @Test
    void startOn02South_whenMoveForward_shouldBeAt01() {
        givenARoverAt(0, 2, "S");
        rover.move("F");
        assertEquals("0:1:S", rover.getPosition());
        rover.move("F");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void startOn00East_whenMoveForward_shouldBeAt10() {
        givenARoverAt(0, 0, "E");
        rover.move("F");
        assertEquals("1:0:E", rover.getPosition());
        rover.move("F");
        assertEquals("2:0:E", rover.getPosition());
    }

    @Test
    void startOn02West_whenMoveForward_shouldBeAt10() {
        givenARoverAt(2, 0, "W");
        rover.move("F");
        assertEquals("1:0:W", rover.getPosition());
        rover.move("F");
        assertEquals("0:0:W", rover.getPosition());
    }

    @Test
    void startOn2North_whenMoveBackwards_shouldBeAt01() {
        givenARoverAt(0, 2, "N");
        rover.move("B");
        assertEquals("0:1:N", rover.getPosition());
        rover.move("B");
        assertEquals("0:0:N", rover.getPosition());
    }

    @Test
    void startOn0South_whenMoveBackwards_shouldBeAt01() {
        givenARoverAt(0, 0, "S");
        rover.move("B");
        assertEquals("0:1:S", rover.getPosition());
        rover.move("B");
        assertEquals("0:2:S", rover.getPosition());
    }

    @Test
    void startOn0West_whenMoveBackwards_shouldBeAt01() {
        givenARoverAt(0, 0, "W");
        rover.move("B");
        assertEquals("1:0:W", rover.getPosition());
        rover.move("B");
        assertEquals("2:0:W", rover.getPosition());
    }

    @Test
    void startOn2East_whenMoveBackwards_shouldBeAt01() {
        givenARoverAt(2, 0, "E");
        rover.move("B");
        assertEquals("1:0:E", rover.getPosition());
        rover.move("B");
        assertEquals("0:0:E", rover.getPosition());
    }

    @Test
    void crossTheVerticalEdgeReturnFromBegin() {
        givenARoverAt(0, 10, "N");
        rover.move("F");
        assertEquals("0:0:N", rover.getPosition());
        givenARoverAt(0, 0, "N");
        rover.move("B");
        assertEquals("0:10:N", rover.getPosition());
        givenARoverAt(0, 0, "S");
        rover.move("F");
        assertEquals("0:10:S", rover.getPosition());
        givenARoverAt(0, 10, "S");
        rover.move("B");
        assertEquals("0:0:S", rover.getPosition());
    }

    @Test
    void crossHorizontalEdgeReturnFromBegin() {
        givenARoverAt(10, 0, "E");
        rover.move("F");
        assertEquals("0:0:E", rover.getPosition());
        givenARoverAt(0, 0, "W");
        rover.move("B");
        assertEquals("10:0:W", rover.getPosition());
        givenARoverAt(0, 0, "E");
        rover.move("B");
        assertEquals("10:0:E", rover.getPosition());
        givenARoverAt(10, 0, "W");
        rover.move("F");
        assertEquals("0:0:W", rover.getPosition());
    }
}
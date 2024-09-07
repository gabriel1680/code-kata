package com.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    private void rollMany(int n, int pins) {
        for (int i = 0; i < n; i++)
            game.roll(pins);
    }

    private void rollSpare() {
        game.roll(5);
        game.roll(5);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    void newGame() {
        assertEquals(0, game.score());
    }

    @Test
    void gutterGame() {
        rollMany(20, 0);
        assertEquals(0, game.score());
    }

    @Test
    void allOnes() {
        rollMany(20, 1);
        assertEquals(20, game.score());
    }

    @Test
    void oneSpare() {
        rollSpare();
        game.roll(2);
        rollMany(17, 0);
        assertEquals(14, game.score());
    }

    @Test
    void oneStrike() {
        rollStrike();
        game.roll(3);
        game.roll(2);
        rollMany(16, 0);
        assertEquals(20, game.score());
    }

    @Test
    void perfectGame() {
        rollMany(12, 10);
        assertEquals(300, game.score());
    }
}
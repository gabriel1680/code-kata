package com.kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TennisGameTest {

    private TennisGame game;

    @BeforeEach
    void setUp() {
        game = new TennisGame();
    }

    private void makePlays(int playerOneScoreTimes, int playerTwoScoreTimes) {
        for (int i = 0; i < playerOneScoreTimes; i++) {
            game.playerOneScores();
        }
        for (int i = 0; i < playerTwoScoreTimes; i++) {
            game.playerTwoScores();
        }
    }

    @Test
    void when0x0() {
        assertEquals("Love all", game.getScore());
    }

    @Test
    void when1x0() {
        game.playerOneScores();
        assertEquals("Fifteen,Love", game.getScore());
    }

    @Test
    void when1x1() {
        makePlays(1, 1);
        assertEquals("Fifteen all", game.getScore());
    }

    @Test
    void when2x1() {
        makePlays(2, 1);
        assertEquals("Thirty,Fifteen", game.getScore());
    }

    @Test
    void when2x3() {
        makePlays(2, 3);
        assertEquals("Thirty,Forty", game.getScore());
    }

    @Test
    void when3x0() {
        makePlays(3, 0);
        assertEquals("Forty,Love", game.getScore());
    }

    @Test
    void when3x3_deuce() {
        makePlays(3, 3);
        assertEquals("Deuce", game.getScore());
    }

    @Test
    void when4x3_advantagePlayerOne() {
        makePlays(4, 3);
        assertEquals("Advantage Player1", game.getScore());
    }

    @Test
    void when5x6_advantagePlayerTwo() {
        makePlays(5, 6);
        assertEquals("Advantage Player2", game.getScore());
    }

    @Test
    void when4x2_playerOneWins() {
        makePlays(4, 2);
        assertEquals("Player1 wins", game.getScore());
    }
}
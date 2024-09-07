package com.bowling;

public class Game {

    private static final int TOTAL_FRAMES = 10;
    private static final int MAX_ONE_FRAME_SCORE = 10;

    private final int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int frameIdx = 0;
        for (int frame = 0; frame < TOTAL_FRAMES; frame++) {
            if (isStrike(frameIdx)) {
                score += MAX_ONE_FRAME_SCORE + getStrikeBonus(frameIdx);
                frameIdx++;
            } else if (isSpare(frameIdx)) {
                score += MAX_ONE_FRAME_SCORE + getSpareBonus(frameIdx);
                frameIdx += 2;
            } else {
                score += getCurrentAndNextRoll(frameIdx);
                frameIdx += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int frameIdx) {
        return rolls[frameIdx] == MAX_ONE_FRAME_SCORE;
    }

    private int getStrikeBonus(int frameIdx) {
        return rolls[frameIdx + 1] + rolls[frameIdx + 2];
    }

    private boolean isSpare(int frameIdx) {
        return getCurrentAndNextRoll(frameIdx) == MAX_ONE_FRAME_SCORE;
    }

    private int getSpareBonus(int frameIdx) {
        return rolls[frameIdx + 2];
    }

    private int getCurrentAndNextRoll(int frameIdx) {
        return rolls[frameIdx] + rolls[frameIdx + 1];
    }
}

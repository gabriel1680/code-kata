package com.bowling;

public class Game {

    private final int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        rolls[currentRoll++] = pins;
    }

    public int score() {
        int score = 0;
        int firstInFrame = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(firstInFrame)) {
                score += 10 + getStrikeBonus(firstInFrame);
                firstInFrame += 1;
            } else if (isSpare(firstInFrame)) {
                score += 10 + getSpareBonus(firstInFrame);
                firstInFrame += 2;
            } else {
                score += rolls[firstInFrame] + rolls[firstInFrame + 1];
                firstInFrame += 2;
            }
        }
        return score;
    }

    private int getSpareBonus(int firstFrame) {
        return rolls[firstFrame + 2];
    }

    private int getStrikeBonus(int firstFrame) {
        return rolls[firstFrame + 1] + rolls[firstFrame + 2];
    }

    private boolean isStrike(int i) {
        return rolls[i] == 10;
    }

    private boolean isSpare(int i) {
        return rolls[i] + rolls[i + 1] == 10;
    }
}

package com.kata;

public class TennisGame {

    private int playerOneScore = 0;
    private int playerTwoScore = 0;

    public String getScore() {
        if (isWin()) {
            return getHighScoredPlayer() + " wins";
        } else if (isDeuce()) {
            return "Deuce";
        } else if (isAdvantage()) {
            return "Advantage " + getHighScoredPlayer();
        } else if (isSamePoints()) {
            return getPoints(playerOneScore) + " all";
        } else {
            return getPoints(playerOneScore) + "," + getPoints(playerTwoScore);
        }
    }

    private boolean isWin() {
        return getPointsDifference() == 2;
    }

    private int getPointsDifference() {
        return Math.abs(playerOneScore - playerTwoScore);
    }

    private String getHighScoredPlayer() {
        return playerOneScore > playerTwoScore ? "Player1" : "Player2";
    }

    private boolean isDeuce() {
        return playerOneScore == 3 && isSamePoints();
    }

    private boolean isAdvantage() {
        return (playerOneScore > 3 || playerTwoScore > 3) &&
                getPointsDifference() == 1;
    }

    private boolean isSamePoints() {
        return playerOneScore == playerTwoScore;
    }

    private String getPoints(int points) {
        return switch (points) {
            case 0 -> "Love";
            case 1 -> "Fifteen";
            case 2 -> "Thirty";
            case 3 -> "Forty";
            default -> "";
        };
    }

    public void playerOneScores() {
        playerOneScore++;
    }

    public void playerTwoScores() {
        playerTwoScore++;
    }
}

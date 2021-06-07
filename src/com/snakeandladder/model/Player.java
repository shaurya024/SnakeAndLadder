package com.snakeandladder.model;

import com.snakeandladder.exception.InvalidPlayerName;

public class Player {

    private String playerName;
    private Integer positionOnBoard;
    // Marked as -1 until user completes the game.
    private Integer winningPosition;

    public Player(String playerName) throws InvalidPlayerName {
        this.playerName = playerName;
        positionOnBoard = 0;
        winningPosition = -1;
        validatePlayerName();
    }

    public Integer movePlayer(Integer incrementInPosition) {
        positionOnBoard += incrementInPosition;
        return positionOnBoard;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setWinningPosition(Integer winningPosition) {
        this.winningPosition = winningPosition;
    }

    public void setPositionOnBoard(Integer positionOnBoard) {
        this.positionOnBoard = positionOnBoard;
    }

    public Integer getPositionOnBoard() {
        return positionOnBoard;
    }

    public Boolean hasWon() {
        return positionOnBoard == 100;
    }

    public Boolean isValidPosition(Integer position) {
        return position >=0 && position <= 100;
    }

    private void validatePlayerName() throws InvalidPlayerName {
        if (playerName == null || playerName.isEmpty()) {
            throw new InvalidPlayerName();
        }
    }
}

package com.snakeandladder.exception;

import com.snakeandladder.model.Player;

public class PlayerAlreadyWon extends Exception {

    public PlayerAlreadyWon(Player player) {
        super(String.format("Player: {} had already won", player.getPlayerName()));
    }
}

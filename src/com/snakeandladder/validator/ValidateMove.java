package com.snakeandladder.validator;

import com.snakeandladder.model.Player;

public class ValidateMove {
    public boolean isValidate(Player player, Integer incrementByPosition) {
        if (player.hasWon())
            return false;
        Integer positionOnBoard = player.getPositionOnBoard();
        return player.isValidPosition(positionOnBoard + incrementByPosition);
    }
}

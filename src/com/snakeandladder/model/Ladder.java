package com.snakeandladder.model;

import com.snakeandladder.exception.InvalidPositioning;

import static com.snakeandladder.constants.ConstantUtils.*;

public class Ladder implements UnNamed{

    private Integer startPosition;
    private Integer endPosition;

    public Ladder(Integer startPosition, Integer endPosition) throws InvalidPositioning {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
        validatePositioning();
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.setPositionOnBoard(endPosition);
    }

    @Override
    public Integer getPointOfAction() {
        return startPosition;
    }

    @Override
    public void validatePositioning() throws InvalidPositioning {
        if (startPosition >= endPosition) {
            throw new InvalidPositioning(String.format(INVALID_POSITION, LADDER));
        }
    }
}

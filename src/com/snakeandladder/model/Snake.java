package com.snakeandladder.model;

import com.snakeandladder.exception.InvalidPositioning;

import static com.snakeandladder.constants.ConstantUtils.*;

public class Snake implements Blocker {

    private Integer headPosition;
    private Integer tailPosition;

    public Snake(Integer headPosition, Integer tailPosition) throws InvalidPositioning {
        this.headPosition = headPosition;
        this.tailPosition = tailPosition;
        validatePositioning();
    }

    @Override
    public void applyOnPlayer(Player player) {
        player.setPositionOnBoard(tailPosition);
    }

    @Override
    public Integer getPointOfAction() {
        return headPosition;
    }

    @Override
    public void validatePositioning() throws InvalidPositioning {
        if (headPosition <= tailPosition) {
            throw new InvalidPositioning(String.format(INVALID_POSITION,
                    SNAKE, String.format(MUST_BE_GREATER, "HEAD", "TAIL")));
        }
    }

    public String toString() {
        return String.format("[%s, %d, %d]", SNAKE, headPosition, tailPosition);
    }
}

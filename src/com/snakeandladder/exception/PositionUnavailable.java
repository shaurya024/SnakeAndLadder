package com.snakeandladder.exception;

public class PositionUnavailable extends Exception {

    public PositionUnavailable(Integer position) {
        super(String.format("Position: {} is already occupied", position));
    }
}

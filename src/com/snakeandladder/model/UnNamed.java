package com.snakeandladder.model;

import com.snakeandladder.exception.InvalidPositioning;

public interface UnNamed {
    void applyOnPlayer(Player player);
    Integer getPointOfAction();
    void validatePositioning() throws InvalidPositioning;
}

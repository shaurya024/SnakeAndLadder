package com.snakeandladder.model;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {

    private static Integer minValueOnDice = 1;
    private static Integer maxValueOnDice = 6;

    public static void initialiseDice(Integer minValue, Integer maxValue) {
        minValueOnDice = minValue;
        maxValueOnDice = maxValue;
    }

    public static Integer rollDice() {
        return ThreadLocalRandom.current().nextInt(minValueOnDice, maxValueOnDice + 1);
    }
}

package com.snakeandladder;

import com.snakeandladder.exception.InvalidPositioning;
import com.snakeandladder.exception.PositionUnavailable;
import com.snakeandladder.model.Ladder;
import com.snakeandladder.model.Player;
import com.snakeandladder.model.Snake;

import java.util.Scanner;

import static com.snakeandladder.constants.ConstantUtils.*;

public class Main {

    private static Scanner inputFromConsole;
    private static GameBoard gameBoard;
    public static void main(String[] args) throws Exception {

        // Scanner for console input.
        inputFromConsole = new Scanner(System.in);
        gameBoard = GameBoard.getInstance();

        configureSnake();
        configureLadder();
        Integer numberOfPlayer = configurePlayer();

        Integer requiredNumberOfWins = 1;
        Controller.start(numberOfPlayer, requiredNumberOfWins);
    }

    private static void configureSnake() throws PositionUnavailable, InvalidPositioning {
        System.out.println(String.format(NUMBER_OF_OBJECT, SNAKE));
        Integer numberOfSnake = inputFromConsole.nextInt();
        while (numberOfSnake-- > 0) {
            System.out.println(String.format("Enter head & tail of %s.", SNAKE));
            Integer head = inputFromConsole.nextInt();
            Integer tail = inputFromConsole.nextInt();
            gameBoard.addBlocker(new Snake(head, tail));
        }
    }

    private static void configureLadder() throws InvalidPositioning, PositionUnavailable {
        System.out.println(String.format(NUMBER_OF_OBJECT, LADDER));
        Integer numberOfLadder = inputFromConsole.nextInt();
        while (numberOfLadder-- > 0) {
            System.out.println(String.format("Enter start & end of %s.", LADDER));
            Integer start = inputFromConsole.nextInt();
            Integer end = inputFromConsole.nextInt();
            gameBoard.addBlocker(new Ladder(start, end));
        }
    }

    private static Integer configurePlayer() throws Exception {
        System.out.println(String.format(NUMBER_OF_OBJECT, PLAYER));
        Integer numberOfPlayer = inputFromConsole.nextInt(),
                numberOfPlayerClone = numberOfPlayer;
        if (numberOfPlayer <= 0) {
            throw new Exception("Number of Players must be greater than 0");
        }
        while (numberOfPlayer-- > 0) {
            System.out.println(String.format("Enter name of %s.", PLAYER));
            String playerName = inputFromConsole.next();
            gameBoard.addPlayer(new Player(playerName));
        }
        return numberOfPlayerClone;
    }
}
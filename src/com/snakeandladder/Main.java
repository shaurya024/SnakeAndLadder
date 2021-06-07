package com.snakeandladder;

import com.snakeandladder.exception.InvalidPositioning;
import com.snakeandladder.exception.PositionUnavailable;
import com.snakeandladder.model.Ladder;
import com.snakeandladder.model.Player;
import com.snakeandladder.model.Snake;

import java.util.Scanner;

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

        Controller.start(numberOfPlayer, 1);
    }

    private static void configureSnake() throws PositionUnavailable, InvalidPositioning {
        Integer numberOfSnake = inputFromConsole.nextInt();
        while (numberOfSnake >= 0) {
            Integer head = inputFromConsole.nextInt();
            Integer tail = inputFromConsole.nextInt();
            gameBoard.addOnBoard(new Snake(head, tail));
        }
    }

    private static void configureLadder() throws InvalidPositioning, PositionUnavailable {
        Integer numberOfLadder = inputFromConsole.nextInt();
        while (numberOfLadder >= 0) {
            Integer start = inputFromConsole.nextInt();
            Integer end = inputFromConsole.nextInt();
            gameBoard.addOnBoard(new Ladder(start, end));
        }
    }

    private static Integer configurePlayer() throws Exception {
        Integer numberOfPlayer = inputFromConsole.nextInt();
        if (numberOfPlayer <= 0) {
            throw new Exception("Number of Players must be greater than 0");
        }
        while (numberOfPlayer >= 0) {
            String playerName = inputFromConsole.next();
            gameBoard.addPlayer(new Player(playerName));
        }
        return numberOfPlayer;
    }
}
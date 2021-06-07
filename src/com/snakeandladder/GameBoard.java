package com.snakeandladder;

import com.snakeandladder.exception.PlayerAlreadyWon;
import com.snakeandladder.exception.PositionUnavailable;
import com.snakeandladder.model.Dice;
import com.snakeandladder.model.Player;
import com.snakeandladder.model.Blocker;
import com.snakeandladder.validator.ValidateMove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    private static GameBoard gameBoard;
    private Map<Integer, Blocker> mapOfBlocker;
    private List<Player> playerList;
    private ValidateMove validateMove;

    private GameBoard(){
        mapOfBlocker = new HashMap<>();
        playerList = new ArrayList<>();
        validateMove = new ValidateMove();
    }

    public static GameBoard getInstance() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }
        return gameBoard;
    }

    public void addBlocker(Blocker blocker) throws PositionUnavailable {
        Integer pointOfAction = blocker.getPointOfAction();
        if (mapOfBlocker.containsKey(pointOfAction)) {
            throw new PositionUnavailable(pointOfAction);
        }
        mapOfBlocker.put(pointOfAction, blocker);
    }

    public void addPlayer(Player player) {
        playerList.add(player);
    }

    public Player removePlayer(Integer indexOfPlayer) {
        Player player = playerList.get(indexOfPlayer);
        playerList.remove(indexOfPlayer);
        return player;
    }

    public Boolean movePlayer(Integer playerIndex) throws PlayerAlreadyWon {
        return movePlayer(playerList.get(playerIndex));
    }

    private Boolean movePlayer(Player player) throws PlayerAlreadyWon {
        if (player.hasWon()) {
            throw new PlayerAlreadyWon(player);
        }

        Boolean playerMoved = false;
        while(!playerMoved) {
            Integer numberOnDice = Dice.rollDice();
            if (validateMove.isValidate(player, numberOnDice)) {
                Integer currPositionOfPlayer = player.getPositionOnBoard();
                player.movePlayer(numberOnDice);
                Integer updatedPositionOfPlayer = player.getPositionOnBoard();
                if (mapOfBlocker.containsKey(updatedPositionOfPlayer)) {
                    Blocker blocker = mapOfBlocker.get(updatedPositionOfPlayer);
                    blocker.applyOnPlayer(player);
                    System.out.print(String.format("Blocker: %s is executed. ", blocker));
                }
                updatedPositionOfPlayer = player.getPositionOnBoard();
                System.out.println(String.format("%s rolled a %d and moved from %d to %d.",
                        player.getPlayerName(), numberOnDice, currPositionOfPlayer, updatedPositionOfPlayer));
                playerMoved = true;
            }
        }

        return player.hasWon();
    }
}

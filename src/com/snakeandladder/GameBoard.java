package com.snakeandladder;

import com.snakeandladder.exception.PlayerAlreadyWon;
import com.snakeandladder.exception.PositionUnavailable;
import com.snakeandladder.model.Dice;
import com.snakeandladder.model.Player;
import com.snakeandladder.model.UnNamed;
import com.snakeandladder.validator.ValidateMove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    private static GameBoard gameBoard;
    private Map<Integer, UnNamed> unNamedOnBoard;
    private List<Player> playerList;
    private ValidateMove validateMove;

    private GameBoard(){
        unNamedOnBoard = new HashMap<>();
        playerList = new ArrayList<>();
        validateMove = new ValidateMove();
    }

    public static GameBoard getInstance() {
        if (gameBoard == null) {
            gameBoard = new GameBoard();
        }
        return gameBoard;
    }

    public void addOnBoard(UnNamed unNamed) throws PositionUnavailable {
        Integer pointOfAction = unNamed.getPointOfAction();
        if (unNamedOnBoard.containsKey(pointOfAction)) {
            throw new PositionUnavailable(pointOfAction);
        }
        unNamedOnBoard.put(pointOfAction, unNamed);
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
        Integer currPositionOfPlayer = player.getPositionOnBoard();
        Integer updatedPositionOfPlayer = -1;
        while(!playerMoved) {
            Integer numberOnDice = Dice.rollDice();
            if (validateMove.isValidate(player, numberOnDice)) {
                player.movePlayer(numberOnDice);
                updatedPositionOfPlayer = player.getPositionOnBoard();
                if (unNamedOnBoard.containsKey(updatedPositionOfPlayer)) {
                    UnNamed unNamed = unNamedOnBoard.get(updatedPositionOfPlayer);
                    unNamed.applyOnPlayer(player);
                }
                updatedPositionOfPlayer = player.getPositionOnBoard();
                System.out.println(String.format("%s rolled a %d and moved from %d to %d",
                        player.getPlayerName(), numberOnDice, currPositionOfPlayer, updatedPositionOfPlayer));
                playerMoved = true;
            }
        }

        return player.hasWon();
    }
}

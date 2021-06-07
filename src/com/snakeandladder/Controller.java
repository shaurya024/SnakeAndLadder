package com.snakeandladder;

import com.snakeandladder.exception.PlayerAlreadyWon;
import com.snakeandladder.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Integer playersCount;
    private static Integer numberOfPlayerWon;
    private static Integer requiredNumberOfPlayerWon;
    private static List<Player> playersWon;

    private static GameBoard gameBoard;

    public static void start(Integer numberOfPlayers, Integer numberOfWins) throws PlayerAlreadyWon {
        playersCount = numberOfPlayers;
        requiredNumberOfPlayerWon = numberOfWins;
        numberOfPlayerWon = 0;
        playersWon = new ArrayList<>();
        gameBoard = GameBoard.getInstance();
        startPlaying();
    }

    private static void startPlaying() throws PlayerAlreadyWon {
        Integer playerIndex = 0;
        while (numberOfPlayerWon < requiredNumberOfPlayerWon) {
            Boolean won = gameBoard.movePlayer(playerIndex);
            if (won) {
                Player player = gameBoard.removePlayer(playerIndex);
                player.setWinningPosition(++numberOfPlayerWon);
                System.out.println(String.format("%s wins the game.", player.getPlayerName()));

                playersWon.add(player);
                numberOfPlayerWon += 1;
                playersCount -= 1;
            } else {
                playerIndex = (playerIndex + 1)%playersCount;
            }
        }
    }
}

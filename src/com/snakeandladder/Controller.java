package com.snakeandladder;

import com.snakeandladder.exception.PlayerAlreadyWon;
import com.snakeandladder.model.Player;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Integer playersCount;
    private static Integer numberOfPlayerWon;
    private static Integer remainingNumberOfWins;
    private static List<Player> playersWon;

    private static GameBoard gameBoard;

    public static void start(Integer numberOfPlayers, Integer requiredNumberOfWins) throws PlayerAlreadyWon {
        playersCount = numberOfPlayers;
        remainingNumberOfWins = requiredNumberOfWins;
        numberOfPlayerWon = 0;
        playersWon = new ArrayList<>();
        gameBoard = GameBoard.getInstance();
        startPlaying();
    }

    private static void startPlaying() throws PlayerAlreadyWon {
        Integer playerIndex = 0;
        while (remainingNumberOfWins > 0) {
            Boolean won = gameBoard.movePlayer(playerIndex);
            if (won) {
                Player player = gameBoard.removePlayer(playerIndex);
                player.setWinningPosition(++numberOfPlayerWon);
                System.out.println(String.format("%s wins the game.", player.getPlayerName()));

                playersWon.add(player);
                remainingNumberOfWins -=1;
                playersCount -= 1;
            } else {
                playerIndex = (playerIndex + 1) % playersCount;
            }
        }
    }
}

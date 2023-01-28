package tauGame.gameBoard;

import io.vavr.control.Try;
import lombok.Getter;

import java.util.Scanner;

import static io.vavr.API.println;

@Getter
public class Game {
    private final GameBoard gameBoard;
    private boolean isGameOver;

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void movePlayer() {
        Scanner in = new Scanner(System.in);
        do {
            isGameOver = false;
            System.out.println("WSAD to move PLAYER - *");
            switch (in.nextLine()) {
                case "s" -> {
                    movePlayerDown();
                    drawBoard();
                }
                case "w" -> {
                    movePlayerUp();
                    drawBoard();
                }
                case "a" -> {
                    movePlayerLeft();
                    drawBoard();
                }
                case "d" -> {
                    movePlayerRight();
                    drawBoard();
                }
                default -> System.out.println("press only WSAD!");
            }
        } while (!isGameOver);
        System.out.println("!!!!!!!!!!!!!YOU WON!!!!!!!!!!!!!");
    }

    public void movePlayerLeft() {
        var xRowSize = gameBoard.getGameBoard().size();
        for (int i = 0; i < xRowSize; i++) {
            var yRowSize = gameBoard.getGameBoard().get(i).size();
            for (int j = 0; j < yRowSize; j++) {
                if (isPlayerOnField(i, j)) {
                    tryToMoveLeft(i, j);
                    return;
                }
            }
        }
    }

    private void tryToMoveLeft(int i, int j) {
        Try.of(() -> gameBoard.getGameBoard().get(i).get(j - 1))
                .onSuccess(field -> {
                    if (field != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i, j - 1);
                        gameBoard.getGameBoard().get(i).set(j - 1, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                });
    }

    public void movePlayerRight() {
        var xRowSize = gameBoard.getGameBoard().size();
        for (int i = 0; i < xRowSize; i++) {
            var yRowSize = gameBoard.getGameBoard().get(i).size();
            for (int j = 0; j < yRowSize; j++) {
                if (isPlayerOnField(i, j)) {
                    tryToMoveRight(i, j);
                    return;
                }
            }
        }
    }

    private boolean isPlayerOnField(int i, int j) {
        return gameBoard.getGameBoard().get(i).get(j) == GameField.PLAYER;
    }

    private void tryToMoveRight(int i, int j) {
        Try.of(() -> gameBoard.getGameBoard().get(i).get(j + 1))
                .onSuccess(field -> {
                    if (field != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i, j + 1);
                        gameBoard.getGameBoard().get(i).set(j + 1, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                });
    }

    public void movePlayerUp() {
        for (int i = 0; i < gameBoard.getGameBoard().size(); i++) {
            for (int j = 0; j < gameBoard.getGameBoard().get(i).size(); j++) {
                if (isPlayerOnField(i, j)) {
                    tryToMovePlayerUp(i, j);
                    return;
                }
            }
        }
    }

    private void tryToMovePlayerUp(int i, int j) {
        Try.of(() -> gameBoard.getGameBoard().get(i - 1).get(j))
                .onSuccess(field -> {
                    if (field != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i - 1, j);
                        gameBoard.getGameBoard().get(i - 1).set(j, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                });
    }

    public void movePlayerDown() {
        for (int i = 0; i < gameBoard.getGameBoard().size(); i++) {
            for (int j = 0; j < gameBoard.getGameBoard().get(i).size(); j++) {
                if (isPlayerOnField(i, j)) {
                    tryToMovePlayerDown(i, j);
                    return;
                }
            }
        }
    }

    private void tryToMovePlayerDown(int i, int j) {
        Try.of(() -> gameBoard.getGameBoard().get(i + 1).get(j))
                .onSuccess(field -> {
                    if (field != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i + 1, j);
                        gameBoard.getGameBoard().get(i + 1).set(j, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                });
    }

    private void drawBoard() {
        for (int i = 0; i < gameBoard.getGameBoard().size(); i++) {
            for (int j = 0; j < gameBoard.getGameBoard().get(i).size(); j++) {
                System.out.print(gameBoard.getGameBoard().get(i).get(j).getFieldMark() + " ");
            }
            println();
        }
    }

    private void detectWin(int i, int j) {
        isGameOver = gameBoard.getGameBoard().get(i).get(j) == GameField.STOP;
    }
}

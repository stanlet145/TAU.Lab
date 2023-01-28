package tauGame.gameBoard;

import java.util.Scanner;

import static io.vavr.API.println;

public class Game {
    private final GameBoard gameBoard;
    private boolean isGameOver;

    public Game(GameBoard gameBoard) {
        this.gameBoard = gameBoard;

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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard.getGameBoard().get(i).get(j) == GameField.PLAYER && j != 0) {
                    if (gameBoard.getGameBoard().get(i).get(j - 1) != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i, j - 1);
                        gameBoard.getGameBoard().get(i).set(j - 1, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                    return;
                }
            }
        }
    }

    public void movePlayerRight() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard.getGameBoard().get(i).get(j) == GameField.PLAYER && j < 4) {
                    if (gameBoard.getGameBoard().get(i).get(j + 1) != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i, j + 1);
                        gameBoard.getGameBoard().get(i).set(j + 1, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                    return;
                }
            }
        }
    }

    public void movePlayerUp() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard.getGameBoard().get(i).get(j) == GameField.PLAYER && i != 0) {
                    if (gameBoard.getGameBoard().get(i - 1).get(j) != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i - 1, j);
                        gameBoard.getGameBoard().get(i - 1).set(j, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                    return;
                }
            }
        }
    }

    public void movePlayerDown() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard.getGameBoard().get(i).get(j) == GameField.PLAYER && i < 4) {
                    if (gameBoard.getGameBoard().get(i + 1).get(j) != GameField.OBSTACLE) {
                        gameBoard.getGameBoard().get(i).set(j, GameField.EMPTY);
                        detectWin(i + 1, j);
                        gameBoard.getGameBoard().get(i + 1).set(j, GameField.PLAYER);
                    } else {
                        System.out.println("watch out, obstacle");
                    }
                    return;
                }
            }
        }
    }

    private void drawBoard() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(gameBoard.getGameBoard().get(i).get(j).getFieldMark() + " ");
            }
            println();
        }
    }

    private void detectWin(int i, int j) {
        isGameOver = gameBoard.getGameBoard().get(i).get(j) == GameField.STOP;
    }
}

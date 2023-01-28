package tauGame.gameBoard;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static io.vavr.API.*;

@Data
public class GameBoard {
    private List<List<GameField>> gameBoard;
    private boolean isStartPresent;
    private boolean isStopPresent;

    public GameBoard() {
        initializeGameBoard();
    }

    public void initializeGameBoard() {
        gameBoard = new ArrayList<>();
        initializeFields();
        System.out.println("-----INITIALIZING PLAYER ON START--------");
        initializePlayer();
    }

    private void initializeFields() {
        for (int i = 0; i < 5; i++) {
            gameBoard.add(new ArrayList<>());
            for (int j = 0; j < 5; j++) {
                gameBoard.get(i).add(Match(getFieldCase(i, j)).of(Case($(0), GameField.EMPTY),
                        Case($(1), GameField.OBSTACLE),
                        Case($(2), GameField.START),
                        Case($(3), GameField.STOP)));
                System.out.print(gameBoard.get(i).get(j).getFieldMark() + " ");
            }
            println();
        }
    }

    private int getFieldCase(int i, int j) {
        Random random = new Random();
        int value = 0;
        if (i == 0 && !isStartPresent) {
            int start = random.nextInt(49);
            if (start > 30) {
                isStartPresent = true;
                return 2;
            }
        } else if (i == 4 && !isStopPresent) {
            int stop = random.nextInt(49);
            if (stop > 30) {
                isStopPresent = true;
                return 3;
            }
        } else {
            int obstacle = random.nextInt(100);
            if (obstacle < 24) {
                return 1;
            }
        }
        if (i == 0 && !isStartPresent && j == 4) {
            return 2;
        }
        if (i == 4 && !isStopPresent && j == 4) {
            return 3;
        }
        return value;
    }

    private void initializePlayer() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (gameBoard.get(i).get(j) == GameField.START) {
                    gameBoard.get(i).set(j, GameField.PLAYER);
                }
                System.out.print(gameBoard.get(i).get(j).getFieldMark() + " ");
            }
            println();
        }
    }
}

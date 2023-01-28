package tauGame.gameBoard;


import lombok.Getter;

@Getter
public enum GameField {
    EMPTY("[ ]"), OBSTACLE("[X]"), START("[S]"), STOP("[F]"), PLAYER("[*]");

    private String fieldMark;

    GameField(String fieldMark) {
        this.fieldMark = fieldMark;
    }
}

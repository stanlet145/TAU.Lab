import tauGame.gameBoard.Game;
import tauGame.gameBoard.GameBoard;

public class MainApplication {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        Game game = new Game(gameBoard);
    }
}

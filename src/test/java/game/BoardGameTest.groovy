package game

import spock.lang.Specification
import tauGame.gameBoard.Game
import tauGame.gameBoard.GameBoard
import tauGame.gameBoard.GameField

class BoardGameTest extends Specification {
    def "moveRightOutsideOfGameBoundsNoExceptionThrownTest"() {
        given:
        def gameFields = List.of(List.of(GameField.EMPTY, GameField.PLAYER))
        def gameBoard = new GameBoard()
        gameBoard.setGameBoard(gameFields)
        def game = new Game(gameBoard);
        when:
        game.movePlayerRight()
        then:
        noExceptionThrown()
    }
}

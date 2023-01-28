package game

import spock.lang.Specification
import tauGame.gameBoard.Game
import tauGame.gameBoard.GameBoard
import tauGame.gameBoard.GameField

class BoardGameTest extends Specification {

    def "movePlayerTest"() {
        given:
        List<List<GameField>> gameFields = new ArrayList<>();
        gameFields.add(lisfOfFields)
        def gameBoard = new GameBoard()
        gameBoard.setGameBoard(gameFields)
        def game = new Game(gameBoard)
        when:
        game.movePlayerRight()
        then:
        gameBoard.getGameBoard().get(0).get(coordinateY) == expectedField
        where:
        coordinateY | expectedField    | lisfOfFields
        1           | GameField.PLAYER | new ArrayList<GameField>(List.of(GameField.PLAYER, GameField.EMPTY))
        0           | GameField.EMPTY  | new ArrayList<GameField>(List.of(GameField.PLAYER, GameField.EMPTY))
        0           | GameField.PLAYER | new ArrayList<GameField>(List.of(GameField.PLAYER, GameField.OBSTACLE))
        2           | GameField.PLAYER | new ArrayList<GameField>(List.of(GameField.EMPTY, GameField.OBSTACLE, GameField.PLAYER))
    }

    def "moveRightOutsideOfGameBoundsNoExceptionThrownTest"() {
        given:
        List<List<GameField>> gameFields = new ArrayList<>();
        gameFields.add(new ArrayList<GameField>(List.of(GameField.PLAYER)))
        def gameBoard = new GameBoard()
        gameBoard.setGameBoard(gameFields)
        def game = new Game(gameBoard)
        when:
        game.movePlayerDown()
        game.movePlayerRight()
        game.movePlayerUp()
        game.movePlayerLeft()
        then:
        noExceptionThrown()
    }

    def "detectWinTest"() {
        given:
        List<List<GameField>> gameFields = new ArrayList<>();
        gameFields.add(new ArrayList<GameField>(List.of(GameField.PLAYER, GameField.OBSTACLE, GameField.EMPTY)))
        gameFields.add(new ArrayList<GameField>(List.of(GameField.EMPTY, GameField.EMPTY, GameField.OBSTACLE)))
        gameFields.add(new ArrayList<GameField>(List.of(GameField.EMPTY, GameField.EMPTY, GameField.STOP)))
        def gameBoard = new GameBoard()
        gameBoard.setGameBoard(gameFields)
        def game = new Game(gameBoard)
        when:
        game.movePlayerDown()
        game.movePlayerDown()
        game.movePlayerRight()
        game.movePlayerRight()
        then:
        game.isGameOver()
        gameBoard.getGameBoard().get(coordinateX).get(coordinateY) == expectedField
        where:
        coordinateX | coordinateY | expectedField
        2           | 2           | GameField.PLAYER
    }


}

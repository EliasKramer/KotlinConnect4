package Game

import GameChip
import GameState
import Logger.GameLogger
import OuterGameLoopStates
import Player.Player
import isInt

class Game(redPlayer: Player, yellowPlayer: Player) {
    private val playerR: Player;
    private val playerY: Player;
    private var board: Board;

    private var currPlayer: Player;

    init {
        playerR = redPlayer;
        redPlayer.setChip(GameChip.RedChip);
        playerY = yellowPlayer;
        yellowPlayer.setChip(GameChip.YellowChip);
        //this duplicate code cannot be avoided. cannot call setupBoard from init
        board = Board(4,4)
        currPlayer = playerR;
    }
    private fun setupNewGame()
    {
        println("initializing new board and setting first player to red")
        println("input board size")
        board = Board(
            getNumberInputFromPlayer("Please enter the length for the board:"),
            getNumberInputFromPlayer("Please enter the width for the board:")
        )
        currPlayer = playerR;
    }
    private fun getNumberInputFromPlayer(inputRequestString: String) : Int
    {
        var firstIteration = true;
        while(true)
        {
            if(firstIteration)
            {
                firstIteration = false;
            }
            else
            {
                println("try again")
            }

            println(inputRequestString);
            val rawInput = readLine() ?: continue;
            if(rawInput == "")
            {
                continue;
            }
            if(!isInt(rawInput))
            {
                continue;
            }

            val intInput: Int = Integer.parseInt(rawInput);

            if(intInput > 9)
            {
                println("too big number")
                continue;
            }
            else if(intInput < 4)
            {
                println("too small number")
                continue;
            }
            return intInput;
        }
    }
    fun start() {
        var instructionForNextLoop: OuterGameLoopStates;
        do
        {
            setupNewGame()
            println("started new game")
            instructionForNextLoop = gameLoop();
        } while(instructionForNextLoop != OuterGameLoopStates.CloseProgram)
    }

    private fun gameLoop() : OuterGameLoopStates
    {
        println("started game loop")
        var currentGameState: GameState;
        GameLogger.newGame(board.getLength(), board.getWidth());
        do {
            println("Current Player's turn: ${currPlayer.getName()}")
            board.print()

            println("see if player has valid inputs")
            if(board.getLegalMoves().isEmpty())
            {
                println("The Game has ended. No valid moves available.")
                GameLogger.gameEndMsg("No Moves left\n")
                return OuterGameLoopStates.None;
            }

            println("getting player input")
            val playerInput = currPlayer.getMove(board.getCopy());// getPlayerInput();

            println("handling special cases")
            if(playerInput.second != OuterGameLoopStates.None)
            {
                println("found special case. returning to outer game loop")
                return playerInput.second;
            }

            currentGameState = executeMove(currPlayer.getChip(), playerInput.first);
            GameLogger.logMove(playerInput.first, currPlayer.getChip());

            handleWin(currentGameState);

            switchCurrentPlayer();
        } while (currentGameState == GameState.Ongoing);

        return OuterGameLoopStates.None;
    }

    private fun handleWin(state: GameState) {
        println("handling potential win")
        if (state == GameState.RedWon) {
            board.print()
            println("--------")
            println("Red won!")
            println("--------")
            GameLogger.gameEndMsg("Red Won\n")
        } else if (state == GameState.YellowWon) {
            board.print()
            println("-----------")
            println("Yellow won!")
            println("-----------")
            GameLogger.gameEndMsg("Yellow Won\n")
        }
    }

    private fun executeMove(chip: GameChip, idx: Int): GameState {
        return board.dropChip(chip, idx);
    }

    private fun switchCurrentPlayer() {
        println("switching current player")
        currPlayer =
            if (currPlayer == playerR) {
                playerY;
            } else {
                playerR;
            }
    }
}
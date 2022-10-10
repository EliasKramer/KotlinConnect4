class Game(redPlayerName: String, yellowPlayerName: String) {
    private val playerR: Player;
    private val playerY: Player;
    private var board: Board;

    private var currPlayer: Player;

    init {
        playerR = Player(redPlayerName, GameChip.RedChip)
        playerY = Player(yellowPlayerName, GameChip.YellowChip)
        //this duplicate code cannot be avoided. cannot call setupBoard from init
        board = Board()
        currPlayer = playerR;
    }
    private fun setupNewGame()
    {
        println("initializing new board and setting first player to red")
        board = Board()
        currPlayer = playerR;
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
        board.print()
        var currentGameState: GameState;
        do {
            println("getting player input")
            val droppingIdx = getPlayerInput();



            currentGameState = executeMove(currPlayer.getChip(), droppingIdx);

            board.print()

            handleWin(currentGameState);

            switchCurrentPlayer();
        } while (currentGameState == GameState.Ongoing);

        return OuterGameLoopStates.NewGame;
    }

    private fun handleWin(state: GameState) {
        if (state == GameState.RedWon) {
            println("Red won!")
        } else if (state == GameState.YellowWon) {
            println("Yellow won!")
        }
    }

    private fun getPlayerInput(): Int{

        while (true) {
            println("getting the player input")
            var rawInput = readLine();

            if (rawInput == null) {
                println("input is required");
                continue;
            }
            handleSpecialInputs(toString());

            val playerInput: String = rawInput;
            if (!isInt(playerInput)) {
                println("input has to be a number")
                continue
            }

            val playerInputIdx: Int = Integer.parseInt(playerInput)-1;

            if (board.idxIsLegal(playerInputIdx)) {
                println("valid input was given by the player")
                return playerInputIdx;
            } else {
                println("Input was not valid");
            }
        }
    }

    private fun isInt(str: String) = str.all { it in '0'..'9' };
    private fun handleSpecialInputs(input: String) {
        println("handling special inputs")
        //TODO
        // "0" should end the game immediately
        // "N\n" should start a new game
        println("no special cases found")
        println()
    }

    private fun executeMove(chip: GameChip, idx: Int): GameState {
        return board.dropChip(chip, idx);
    }

    private fun switchCurrentPlayer() {
        currPlayer =
            if (currPlayer == playerR) {
                playerY;
            } else {
                playerR;
            }
    }
}
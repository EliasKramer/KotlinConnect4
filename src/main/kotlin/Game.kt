class Game(redPlayerName: String, yellowPlayerName: String) {
    private val playerR: Player;
    private val playerY: Player;
    private val board: Board;

    private var currPlayer: Player;

    init {
        playerR = Player(redPlayerName, GameChip.RedChip)
        playerY = Player(yellowPlayerName, GameChip.YellowChip)
        board = Board(6,6)

        currPlayer = playerR;
    }

    fun start()
    {
        var currentGameState = GameState.Ongoing;
        do
        {

            switchCurrentPlayer();
        }while(currentGameState == GameState.Ongoing);
    }

    private fun getPlayerMoveAndExecuteIt(): GameState
    {
        val playerMoveIdx = currPlayer.getMove();

        return board.dropChip(currPlayer.getChip(), playerMoveIdx);

    }

    private fun switchCurrentPlayer()
    {

    }
}
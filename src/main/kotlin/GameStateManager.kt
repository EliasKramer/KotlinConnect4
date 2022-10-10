public final class GameStateManager {
    public fun playerMustGiveValidInput(gameState: GameState) : Boolean
    {
        return gameState == GameState.InputInvalid ||
                gameState == GameState.CouldNotDropChip;
    }

    public fun gameIsFinished(gameState: GameState) : Boolean
    {
        return gameState == GameState.RedWon ||
                gameState == GameState.YellowWon;
    }
}
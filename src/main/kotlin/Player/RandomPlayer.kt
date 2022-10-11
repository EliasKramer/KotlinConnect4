package Player

import Game.Board
import OuterGameLoopStates
import kotlin.random.Random

class RandomPlayer(_name: String) : Player(_name) {
    override fun getMove(board: Board): Pair<Int, OuterGameLoopStates> {
        val allMoves = board.getLegalMoves();
        val chosenMove = Random.nextInt(0, allMoves.size-1);

        return Pair(chosenMove, OuterGameLoopStates.None);
    }
}
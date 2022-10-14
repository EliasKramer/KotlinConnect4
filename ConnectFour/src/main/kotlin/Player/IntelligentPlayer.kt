package Player

import Game.Board
import GameChip
import OuterGameLoopStates
import oppositeColorChip
import kotlin.random.Random

class IntelligentPlayer(_name: String) : Player(_name) {
    private var _nodesSearched = 0;

    override fun getMove(board: Board): Pair<Int, OuterGameLoopStates> {
        _nodesSearched = 0;
        val allMoves = board.getLegalMoves();
        var chosenMove = 0;
        var bestMoveScore = Int.MIN_VALUE;
        val depth = 8;

        var multiplier =
            if (_chip == GameChip.YellowChip) {
                -1;
            } else {
                1
            }

        for (currIdx in allMoves) {
            val copyBoard = board.getCopy();
            val gameStateAfterDropping = copyBoard.dropChip(_chip, currIdx);

            var currEval = when (gameStateAfterDropping) {
                GameState.RedWon -> {
                    (1000 + depth + 1) * multiplier;
                }
                GameState.YellowWon -> {
                    -(1000 + depth + 1) * multiplier;
                }
                else -> {
                    multiplier * getRecursiveScoreOfMove(copyBoard, oppositeColorChip(_chip), depth - 1);
                }
            }
            val oneBasedIdx = currIdx + 1;
            println("move: $oneBasedIdx has score: $currEval")

            if (currEval > bestMoveScore) {
                bestMoveScore = currEval;
                chosenMove = currIdx;
            }
        }
        println("Intelligent Player searched $_nodesSearched nodes")
        return Pair(chosenMove, OuterGameLoopStates.None);
    }

    private fun getRecursiveScoreOfMove(board: Board, gameChip: GameChip, depth: Int): Int {
        if (depth == 0) {
            return 0;
        } else {
            val allLegalMoves = board.getLegalMoves();
            var bestMoveScore = Int.MIN_VALUE;

            for (currIdx in allLegalMoves) {
                _nodesSearched++;
                val copyBoard = board.getCopy();
                val retVal = copyBoard.dropChip(gameChip, currIdx);

                if (retVal == GameState.RedWon) {
                    return (1000 + depth);
                } else if (retVal == GameState.YellowWon) {
                    return -(1000 + depth);
                }

                val currEval = -getRecursiveScoreOfMove(copyBoard, oppositeColorChip(gameChip), depth - 1);

                if (currEval > bestMoveScore) {
                    bestMoveScore = currEval;
                }
            }
            return bestMoveScore;
        }
    }
    protected override fun getPlayerType(): String {
        return "(Intelligent Player)";
    }
}
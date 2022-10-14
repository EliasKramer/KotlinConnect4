package Game

import GameChip
import GameState
import java.lang.Exception
import java.util.LinkedList

class Board(
    private val _length: Int,
    private val _width: Int
) {
    private val _internalBoard: Array<Array<GameChip>> = Array(_length) { Array(_width) { GameChip.Empty } }

    public fun getCopy() : Board{
        val board = Board(_length, _width);

        for(length in 0 until _length)
        {
            for(width in 0 until _width)
            {
                board._internalBoard[length][width] = _internalBoard[length][width];
            }
        }

        return board;
    }

    fun print() {
        var resultStr = "|";
        for(i in 0 until _width)
        {
            val currPosVal = i+1
            resultStr += "$currPosVal|";
        }
        resultStr += "\n"
        for (row in _internalBoard) {
            resultStr += "|";
            for (currChip in row) {
                resultStr += (currChip.value);
                resultStr += "|";
            }
            resultStr += "\n";
        }
        println(resultStr);
    }

    fun getLength(): Int {
        return _length;
    }
    fun getWidth(): Int {
        return _width;
    }
    fun getLegalMoves(): List<Int> {
        var retVal = LinkedList<Int>();
        for (i in 0 until _width) {
            if (idxIsLegal(i)) {
                retVal.add(i);
            }
        }
        return retVal;
    }

    fun idxIsLegal(idx: Int): Boolean {
        val retVal = idx in 0 until _width &&
                (_internalBoard[0][idx] == GameChip.Empty);
        return retVal;
    }

    fun dropChip(gameChip: GameChip, widthIdx: Int): GameState {
        var colToPlace = -1;
        //println("iterating from the bottom up at the given index, till there is a empty spot where the chip can go to")
        for (i in _length - 1 downTo 0) {
            if (_internalBoard[i][widthIdx] == GameChip.Empty) {
                colToPlace = i;
                break;
            }
        }
        if (colToPlace == -1) {
            throw Exception("no valid idx to drop was found in the execution. $widthIdx was not valid")
        }

        //println("placed chip")
        _internalBoard[colToPlace][widthIdx] = gameChip;

        //println("evaluating board")
        return evaluateBoard(colToPlace, widthIdx, gameChip);
    }

    private fun evaluateBoard(col: Int, row: Int, chip: GameChip): GameState {
        //println("check if 4 are connected to the current dropped chip")
        val currPlayerWon: Boolean =
            checkIf4Connected(col, row, 0, 1, chip) ||
                    checkIf4Connected(col, row, 1, 0, chip) ||
                    checkIf4Connected(col, row, -1, 1, chip) ||
                    checkIf4Connected(col, row, 1, 1, chip);

        if (currPlayerWon) {
            return if (chip == GameChip.RedChip) {
                //println("red won")
                GameState.RedWon;
            } else {
                //println("yellow won")
                GameState.YellowWon;
            }
        }

        //println("the game is still ongoing")
        return GameState.Ongoing;
    }

    private fun checkIf4Connected(
        col: Int, row: Int,
        colOffset: Int, rowOffset: Int,
        chip: GameChip
    ): Boolean {
        var foundConnected = 0;
        val dirMult = arrayOf<Int>(1, -1);

        //println("checking in the given direction")
        //println("if the there are 3 or more neighbor pieces in the given direction then the game is won")

        for (currDirMult in dirMult) {
            var currCol = col + (colOffset * currDirMult);
            var currRow = row + (rowOffset * currDirMult);

            while (posIsOnBoard(currCol, currRow)) {
                if (_internalBoard[currCol][currRow] == chip) {
                    foundConnected++;
                } else {
                    break;
                }
                currCol += (colOffset * currDirMult);
                currRow += (rowOffset * currDirMult);
            };
        }

        //3 instead of 4 because the one chip the player just added is always considered
        return foundConnected >= 3;
    }

    private fun posIsOnBoard(col: Int, row: Int): Boolean {
        return col in 0 until _length &&
                row in 0 until _width;
    }
}
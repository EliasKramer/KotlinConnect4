class Board {
    val _length = 5;
    val _width = 6;
    val _internalBoard = Array(_length) { Array(_width) { GameChip.Empty } };

    fun print() {
        var resultStr = "";
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

    fun dropChip(gameChip: GameChip, widthIdx: Int): GameState {
        if (widthIdx >= _width || widthIdx < 0)
            return GameState.InputInvalid;

        var colToPlace = -1;
        for (i in _length - 1 downTo 0) {
            if (_internalBoard[i][widthIdx] == GameChip.Empty) {
                colToPlace = i;
                break;
            }
        }
        if (colToPlace == -1) {
            return GameState.CouldNotDropChip;
        }

        _internalBoard[colToPlace][widthIdx] = gameChip;

        return evaluateBoard(colToPlace, widthIdx, gameChip);
    }

    private fun evaluateBoard(col: Int, row: Int, chip: GameChip): GameState {

        val currPlayerWon: Boolean =
            checkIf4Connected(col, row, 0, 1, chip) ||
                    checkIf4Connected(col, row, 1, 0, chip) ||
                    checkIf4Connected(col, row, 1, 1, chip);

        if (currPlayerWon) {
            return if (chip == GameChip.RedChip) {
                GameState.RedWon;
            } else {
                GameState.YellowWon;
            }
        }

        return GameState.Ongoing;
    }

    private fun checkIf4Connected(
        col: Int, row: Int,
        colOffset: Int, rowOffset: Int,
        chip: GameChip
    ): Boolean {
        var foundConnected = 0;
        val dirMult = arrayOf<Int>(1, -1);

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
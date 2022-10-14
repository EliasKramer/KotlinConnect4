package Player

import Game.Board
import GameChip
import OuterGameLoopStates
import isInt

open class Player(
    private val _name: String
) {
    protected var _chip: GameChip = GameChip.Empty;



    open fun getMove(board: Board): Pair<Int, OuterGameLoopStates>{
        while (true) {
            println("getting the player input")
            val rawInput = readLine();

            println("checking if input empty")
            if (rawInput == null || rawInput == "") {
                println("input was empty")
                println("input is required");
                continue;
            }
            println("input was not empty")

            //handle special inputs
            println("checking for special cases")
            if(rawInput == "0")
            {
                println("special case: close program was detected")
                return Pair(-1, OuterGameLoopStates.CloseProgram);
            }
            else if(rawInput == "N"){
                println("special case: new game was detected")
                return Pair(-1, OuterGameLoopStates.NewGame)
            }

            println("converting string? to string")
            val playerInput: String = rawInput;

            println("checking if input is a number")
            if (!isInt(playerInput)) {
                println("input has to be a number")
                continue
            }

            println("converting input to a number")
            val playerInputIdx: Int = Integer.parseInt(playerInput)-1;

            println("checking if the number is a legal index")
            if (board.idxIsLegal(playerInputIdx)) {
                println("valid input was given by the player")
                return Pair(playerInputIdx, OuterGameLoopStates.None);
            } else {
                println("Input was not valid");
            }
        }
    }
    public fun setChip(gameChip: GameChip)
    {
        _chip = gameChip;
    }
    public fun getName(): String{
        return _chip.value + " " + getPlayerType() + " " + _name;
    }
    public fun getChip(): GameChip {
        return _chip;
    }

    protected open fun getPlayerType(): String {
        return "(Normal Player)";
    }
}
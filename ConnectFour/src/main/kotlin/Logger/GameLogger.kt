package Logger

import GameChip
import java.io.File

class GameLogger {

    companion object {
        private val path = "./logs/logged_games.txt";
        private val file = File(path);
        fun logMove(input: Int, chip: GameChip) {
            val stringToAdd = chip.value + ": " + (input+1) + "\n";
            addToLog(stringToAdd);
        }

        fun newGame(length: Int, width: Int) {
            val stringToAdd =
                "--------\n" +
                        "NEW GAME (l" + length + ":w" + width + ")\n" +
                        "________\n";
            addToLog(stringToAdd);
        }

        fun gameEndMsg(value: String) {
            addToLog("The game has ended:\n" +
                    "$value");
        }

        private fun addToLog(value: String) {
            file.appendText(value);
        }
    }
}
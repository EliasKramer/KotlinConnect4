import Game.Game
import Player.IntelligentPlayer
import Player.Player

fun main() {
    val game = Game(Player("Human"), IntelligentPlayer("Unoptimized Minimax"));
    game.start();
}

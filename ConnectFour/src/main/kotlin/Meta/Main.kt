import Game.Game
import Player.IntelligentPlayer
import Player.Player

fun main() {
    val game = Game(IntelligentPlayer("Unoptimized Minimax"), IntelligentPlayer("Unoptimized Minimax"));
    game.start();
}

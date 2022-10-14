import Game.Game
import Player.IntelligentPlayer
import Player.Player
import Player.RandomPlayer

fun main() {
    val game = Game(Player("Red Player"), Player("Intelligent Yellow Player"));
    game.start();
}
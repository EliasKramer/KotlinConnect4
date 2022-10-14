# Kotlin Connect 4

## The Game can be played in different ways.
### There are always 2 Player playing this game, but there are lots of different options, that you can choose from.
### Players :
- Player
    - This is a Player, that you can control
    - Type the column number to drop your chip accordingly
    - Type "N" in order to start a new game
    - Type "0" to end the program
- Random Player
    - Gets the legal moves and plays a random one
- Intelligent Player
    - The Intelligent Player is an unoptimized Minimax Algorithm (No Alpha Beta Pruning Implemented yet)
    - It gets all the legal Moves and plays the best it can find. If all Moves seem equal, it chooses a random move.
fun main() {
    val board = Board();
    board.dropChip(GameChip.RedChip, 0);
    board.dropChip(GameChip.RedChip, 5);
    board.dropChip(GameChip.RedChip, 5);
    board.dropChip(GameChip.YellowChip, 5);
    board.dropChip(GameChip.RedChip, 5);
    val lastState: GameState = board.dropChip(GameChip.RedChip, 5);

    board.print();

    if(lastState == GameState.RedWon)
    {
        println("red won");
    }
}
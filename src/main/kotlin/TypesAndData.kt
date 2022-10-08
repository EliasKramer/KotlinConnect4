enum class GameChip (val value: String){
    RedChip("R"),
    YellowChip("Y"),
    Empty(" ")
};

enum class GameState {
    RedWon,
    YellowWon,
    Ongoing,
    CouldNotDropChip,
    InputInvalid
}
fun isInt(str: String) = str.all { it in '0'..'9' };
fun oppositeColorChip(gameChip: GameChip): GameChip {
    return when (gameChip) {
        GameChip.RedChip -> {
            GameChip.YellowChip
        }
        GameChip.YellowChip -> {
            GameChip.RedChip
        }
        else -> {
            throw Exception("cannot convert a non-color chip")
        }
    }
}
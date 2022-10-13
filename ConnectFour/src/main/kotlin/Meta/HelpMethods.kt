fun isInt(str: String) = str.all { it in '0'..'9' };
fun oppositeColorChip(gameChip: GameChip): GameChip {
    if(gameChip == GameChip.RedChip)
    {
        return GameChip.YellowChip
    }
    else if(gameChip == GameChip.YellowChip)
    {
        return GameChip.RedChip
    }
    else{
        throw Exception("cannot convert a non-color chip")
    }
}
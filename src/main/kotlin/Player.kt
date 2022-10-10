class Player(
    private val _name: String,
    private val _chip: GameChip
) {
    public fun getMove(): Int
    {
        return 0;
    }

    public fun getName(): String
    {
        return _name;
    }
    public fun getChip(): GameChip
    {
        return _chip;
    }
}
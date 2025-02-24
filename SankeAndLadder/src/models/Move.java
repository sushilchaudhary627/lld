package models;

public class Move {
    private final Player player;
    private final Tile tile;

    public Move(Player player, Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public Player getPlayer() {
        return player;
    }

    public Tile getTile() {
        return tile;
    }
}

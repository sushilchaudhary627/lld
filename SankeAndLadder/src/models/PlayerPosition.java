package models;

public class PlayerPosition {
    private Player player;
    private Tile tile;

    public PlayerPosition(Player player, Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public String toString() {
        return "PlayerPosition{" +
            "player=" + player.getName() +
            ", tile=" + tile.getPosition() +
            '}';
    }
}

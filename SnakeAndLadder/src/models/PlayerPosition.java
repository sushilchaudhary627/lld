package models;

public class PlayerPosition {
    Player player;
    Cell cell;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    @Override
    public String toString() {
        return "PlayerPosition{" +
                "player=" + player +
                ", cell=" + cell +
                '}';
    }
}

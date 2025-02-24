package models;

public abstract class Tile {
    private final Integer position;

    protected Tile(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public abstract Integer getNextPosition();
}

package models;

public class SnakeTile  extends  Tile {
    private final Integer to;

    public SnakeTile(Integer from , Integer to) {
        super(from);
       this.to = to;
    }

    @Override
    public Integer getNextPosition() {
        return to;
    }
}

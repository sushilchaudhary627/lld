package models;

public class LadderTile  extends  Tile{
    private final Integer to;

    public LadderTile(Integer from , Integer to) {
        super(from);
        this.to = to;
    }

    @Override
    public Integer getNextPosition() {
        return to;
    }
}

package models;

public class PlainTile extends  Tile {

    public PlainTile(Integer from) {
        super(from);
    }

    @Override
    public Integer getNextPosition() {
        return super.getPosition();
    }
}

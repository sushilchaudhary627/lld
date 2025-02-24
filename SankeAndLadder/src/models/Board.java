package models;

import java.util.List;

public class Board {
    private final Integer boardId;
    private List<Tile> tiles;
    public  Board(Integer boardId, List<Tile>tiles){
        this.boardId = boardId;
        this.tiles = tiles;
    }

    public Tile getTile(int position){
        return tiles.stream()
            .filter( t -> t.getPosition().equals(position)).
            findFirst().orElseThrow();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public boolean isWinningTile(Tile tile){
        return tiles.getLast().getPosition().equals(tile.getPosition());
    }
}

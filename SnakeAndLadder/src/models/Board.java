package models;

import java.util.List;
import java.util.Optional;

public class Board {
    private Integer boardId;
    List<Cell> cells;
    public Board(Integer boardId, List<Cell>cells){
        this.cells = cells;
        this.boardId = boardId;
    }

    public Optional<Cell> findCellByPosition(Integer position){
        return cells.stream().filter((Cell cell) -> cell.getPosition().equals(position)).findFirst();
    }

    public Integer getBoardId() {
        return boardId;
    }

    public List<Cell> getCells() {
        return cells;
    }
    @Override
    public String toString(){
        StringBuilder log = new StringBuilder();
        log.append("Board:").append(getBoardId()).append("\n");
        for(Cell cell:cells){
            log.append(cell).append("\n");
        }
        return log.toString();
    }

    public Cell getStartCell() {
        return cells.getFirst();
    }

    public Cell getWinningCeil(){
        return cells.getLast();
    }
}

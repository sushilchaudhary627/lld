package models;

public class LadderCell extends Cell {
    private Integer nextPosition;

    public LadderCell(Integer position, String label, Integer nextPosition) {
        super(position, label);
        this.nextPosition = nextPosition;
    }

    @Override
    public Integer getNextPosition(Integer diceValue) {
        return nextPosition;
    }

    @Override
    public String toString(){
        return "ladder: " + super.position + " - " + nextPosition;
    }
}

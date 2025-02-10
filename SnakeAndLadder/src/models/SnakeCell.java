package models;

public class SnakeCell extends Cell{
    private Integer nextPosition;

    public SnakeCell(Integer position, String label, Integer nextPosition) {
        super(position, label);
        this.nextPosition = nextPosition;
    }

    @Override
    public Integer getNextPosition(Integer diceValue) {
        return nextPosition;
    }

    @Override
    public String toString(){
        return "Snake: " + super.position + " - " + nextPosition;
    }
}

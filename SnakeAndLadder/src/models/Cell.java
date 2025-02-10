package models;

public abstract class Cell {
    protected Integer position;
    private String label;
    public Cell(Integer position, String label){
        this.label = label;
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public abstract Integer getNextPosition(Integer diceValue);
}

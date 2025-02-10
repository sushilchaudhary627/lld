package models;

import constant.BoardConstant;

public class NormalCell extends Cell{
    public NormalCell(Integer position, String label) {
        super(position, label);
    }


    @Override
    public Integer getNextPosition(Integer diceValue) {
        return Math.min(super.position+diceValue, BoardConstant.MAX_ALLOWED_CELL_POSITION);
    }

    @Override
    public String toString(){
        return "cell: " + super.position;
    }
}

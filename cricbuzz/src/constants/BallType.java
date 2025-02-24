package constants;

import java.util.Objects;

public enum BallType {
    ONE(1), TWO(2), THREE(3),SIXES(6), FOUR(4), WIDE(0), WICKET(-1), FIVE(5);
    private int score;
    BallType(int score){
        this.score = score;
    }


    public int getPositiveScore(){
        return Math.max(0,score);
    }
}

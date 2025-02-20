package constants;

public enum BallType {
    SIX("six", 6),
    FIVE("five", 5),
    FOUR("four",4),
    THREE("three", 3),
    TWO("two", 2),
    ONE("one", 1),
    WICKET("wicket",-1 ),
    WIDE("wide", 1);

    String  type;
    Integer score;
    BallType(String type, Integer score){
        this.score = score;
        this.type = type;
    }


    public String getType() {
        return type;
    }

    public Integer getScore() {
        return Math.max(score, 0);
    }
}

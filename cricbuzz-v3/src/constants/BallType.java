package constants;

public enum BallType {
    SIX("six", 6),
    FIVE("fivc", 5),
    FOUR("four", 4),
    THREE("three", 3),
    TWO("two", 2),
    ONE("one", 1),
    WICKET("wicket", 0),
    NO_BALL("no_ball", 1),
    WIDE("wide", 1);
    private final String type;
    private final int score;


    BallType(String type, int score) {
        this.type = type;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public static boolean  isExtraRun(BallType ballType){
        return ballType == BallType.WIDE || ballType == BallType.NO_BALL;
    }
}

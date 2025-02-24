package constants;

public enum BallType {
    SIX("six", 6),
    FIVE("five", 5),
    FOUR("four", 4),
    THREE("three", 3),
    TWO("two", 2),
    ONE("one", 1),
    WIDE("wide", 0),
    NO_BALL("no ball", 0),
    WICKET("wicket", 0);

    private final Integer score; // Made final
    private final String type;   // Made final

    BallType(String type, Integer score) {
        this.type = type;
        this.score = score;
    }

    public String getType() {
        return type;
    }

    public Integer getScore() {
        return score;
    }

    public static boolean isExtraRun(BallType ballType) {  // Changed Boolean -> boolean
        return ballType == NO_BALL || ballType == WIDE;
    }
}

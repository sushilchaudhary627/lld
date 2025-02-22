package constants;

public enum BallType {
    SIX("six",6),
    FIVE("five", 5),
    FOUR("four", 4),
    THREE("three", 3),
    TWO("two", 2),
    ONE("ONE", 1),
    WICKET("wicket", 0),
    WIDE("wide", 1);


    private final String value;
    private final int runs;

    BallType(String value, int runs) {
        this.value = value;
        this.runs = runs;
    }

    public String getValue() {
        return value;
    }

    public int getRuns() {
        return runs;
    }

    public static boolean isExtra(BallType ballType){
        return ballType == WIDE;
    }
}

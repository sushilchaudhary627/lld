package dto;

public class PlayerScore {
    String playerName;
    Integer sixRunCount;
    Integer fourRunCount;
    Integer totalBallFaced;
    Integer runs;

    public PlayerScore(String playerName, Integer sixRunCount, Integer fourRunCount, Integer totalBallFaced, Integer runs) {
        this.playerName = playerName;
        this.sixRunCount = sixRunCount;
        this.fourRunCount = fourRunCount;
        this.totalBallFaced = totalBallFaced;
        this.runs = runs;
    }

    @Override
    public String toString() {
        return "PlayerScore{" +
                "playerName='" + playerName + '\'' +
                ", sixRunCount=" + sixRunCount +
                ", fourRunCount=" + fourRunCount +
                ", totalBallFaced=" + totalBallFaced +
                ", runs=" + runs +
                '}';
    }
}

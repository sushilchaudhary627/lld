package dto;

public class TeamScore {
    String teamName;
    Integer score;
    Integer wicketCount;
    Integer totalOverCompleted;

    public TeamScore(String teamName, Integer score, Integer wicketCount, Integer totalOverCompleted) {
        this.teamName = teamName;
        this.score = score;
        this.wicketCount = wicketCount;
        this.totalOverCompleted = totalOverCompleted;
    }

    @Override
    public String toString() {
        return "TeamScore{" +
                "teamName='" + teamName + '\'' +
                ", score=" + score +
                ", wicketCount=" + wicketCount +
                ", totalOverCompleted=" + totalOverCompleted +
                '}';
    }
}

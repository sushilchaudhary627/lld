package dto;

import java.util.List;

public class TeamScoreCard {
    String name;
    List<PlayerScoreDetail> playerScoreDetaillist;
    int totalRuns;
    int wicketCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerScoreDetail> getPlayerScoreDetaillist() {
        return playerScoreDetaillist;
    }

    public void setPlayerScoreDetaillist(List<PlayerScoreDetail> playerScoreDetaillist) {
        this.playerScoreDetaillist = playerScoreDetaillist;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }

    public int getWicketCount() {
        return wicketCount;
    }

    public void setWicketCount(int wicketCount) {
        this.wicketCount = wicketCount;
    }

    @Override
    public String toString() {
        return "TeamScoreCard{" +
            "name='" + name + '\'' +
            ", playerScoreDetaillist=" + playerScoreDetaillist +
            ", totalRuns=" + totalRuns +
            ", wicketCount=" + wicketCount +
            '}';
    }
}

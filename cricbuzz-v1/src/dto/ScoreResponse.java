package dto;

import java.util.Map;

public class ScoreResponse {
    TeamScore teamScore;
    Map<String,PlayerScore> playerScoreMap;

    public ScoreResponse(TeamScore teamScore, Map<String, PlayerScore> playerScoreMap) {
        this.teamScore = teamScore;
        this.playerScoreMap = playerScoreMap;
    }

    @Override
    public String toString() {
        return "ScoreResponse{" +
                "teamScore=" + teamScore +
                ", playerScoreMap=" + playerScoreMap +
                '}';
    }
}

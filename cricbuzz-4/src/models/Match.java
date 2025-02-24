package models;

import java.util.List;

public class Match {
    private final Long matchId;
    private String matchName;
    private List<Team> teams;
    private Team winner;
    private Integer totalMatchOver;

    public Match(Long matchId, Integer totalMatchOver) {
        this.matchId = matchId;
        this.totalMatchOver = totalMatchOver;
    }

    public Long getMatchId() {
        return matchId;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public void setWinner(Team team) {
        this.winner = team;
    }

    public Team getWinner() {
        return winner;
    }

    public Integer getTotalMatchOver() {
        return totalMatchOver;
    }
}

package models;

import java.util.List;

public class Match {
    private final Long id;
    private String matchName;
    private List<Team> teams;
    private Team winner;
    public Match(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }
}

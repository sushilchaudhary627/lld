package models;

import java.util.List;

public class Match {
    private final Integer matchId;
    private String name;
    private List<Team> teams;
    public Match(Integer matchId, String name) {
        this.matchId = matchId;
        this.name = name;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", name='" + name + '\'' +
                ", teams=" + teams +
                '}';
    }
}

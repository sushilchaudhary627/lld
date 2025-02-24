package models;

import java.util.List;

public class Match {
    private final Long id;
    private List<Team> teams;
    private Team winner;
    private String name;
    private Integer totalOvers;

    public Match(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
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

    public String getName() {
        return name;
    }

    public Integer getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(Integer totalOvers) {
        this.totalOvers = totalOvers;
    }
}

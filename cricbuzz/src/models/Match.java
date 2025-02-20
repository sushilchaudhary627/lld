package models;

import java.util.ArrayList;
import java.util.*;

public class Match {
    private final Integer matchId;
    private Integer totalOvers;
    private Team teamA;
    private Team teamB;
    public Match(Integer matchId, Integer totalOvers) {
        this.matchId = matchId;
        this.totalOvers = totalOvers;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getTotalOvers() {
        return totalOvers;
    }

    public void setTotalOvers(Integer totalOvers) {
        this.totalOvers = totalOvers;
    }

    public Team getTeamA() {
        return teamA;
    }

    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }

    public Team getTeamB() {
        return teamB;
    }

    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", totalOvers=" + totalOvers +
                ", teamA=" + teamA +
                ", teamB=" + teamB +
                '}';
    }
}

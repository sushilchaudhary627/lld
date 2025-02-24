package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final Integer teamId;
    private final List<MatchOver> matchOvers;
    private final List<Player>players;
    private String name;
    private Player striker;
    private Player nonStriker;

    public Team(Integer teamId, String name, List<Player> players) {
        this.teamId = teamId;
        this.players = players;
        this.name = name;
        this.matchOvers = new ArrayList<>();
    }

    public void addMatchOver(MatchOver matchOver){
        matchOvers.add(matchOver);
    }

    public List<MatchOver>getMatchOvers(){
        return matchOvers;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Integer getTeamId() {
        return teamId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player getStriker() {
        return striker;
    }

    public void setStriker(Player striker) {
        this.striker = striker;
    }

    public Player getNonStriker() {
        return nonStriker;
    }

    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamId=" + teamId +
                ", matchOvers=" + matchOvers +
                ", players=" + players +
                ", name='" + name + '\'' +
                ", striker=" + striker +
                ", nonStriker=" + nonStriker +
                '}';
    }
}

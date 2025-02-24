package models;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final Integer id;
    private String name;
    private List<Player> players;
    private Player striker;
    private Player nonStriker;
    private List<MatchOver>matchOvers;
    private List<Player>playersAvailable;

    public Team(Integer id, String name) {
        this.id = id;
        this.name = name;
        this.matchOvers = new ArrayList<>();
    }

    public void addNewMatchOver(MatchOver matchOver){
        matchOvers.add(matchOver);
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
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

    public List<MatchOver> getMatchOvers() {
        return matchOvers;
    }

    public void setMatchOvers(List<MatchOver> matchOvers) {
        this.matchOvers = matchOvers;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", players=" + players +
                ", striker=" + striker +
                ", nonStriker=" + nonStriker +
                ", matchOvers=" + matchOvers +
                '}';
    }

    public List<Player> getPlayersAvailable() {
        return playersAvailable;
    }

    public void setPlayersAvailable(List<Player> playersAvailable) {
        this.playersAvailable = playersAvailable;
    }
}

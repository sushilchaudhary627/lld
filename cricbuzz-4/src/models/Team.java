package models;

import constants.BattingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Team {
    private final Long id;
    private String name;
    private List<Player> players;
    private Queue<Player> battingOrder;
    private Player striker;
    private Player nonStriker;
    private BattingStatus battingStatus;
    private List<MatchOver>matchOvers;
    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
        this.matchOvers = new ArrayList<>();
    }

    public Long getId() {
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

    public Queue<Player> getBattingOrder() {
        return battingOrder;
    }

    public void setBattingOrder(Queue<Player> battingOrder) {
        this.battingOrder = battingOrder;
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

    public BattingStatus getBattingStatus() {
        return battingStatus;
    }

    public void setBattingStatus(BattingStatus battingStatus) {
        this.battingStatus = battingStatus;
    }

    public void addNewMatchOver(MatchOver matchOver){
        matchOvers.add(matchOver);
    }

    public List<MatchOver> getMatchOvers() {
        return matchOvers;
    }
}

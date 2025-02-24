package models;

import constants.BattingStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Team {
    private final Long id;
    private List<MatchOver>matchOverList;
    private List<Player> players;
    private Queue<Player>battingOrder;
    private String name;
    private Player striker;
    private Player nonStriker;
    private BattingStatus battingStatus;

    public Team(Long id, String name) {
        this.id = id;
        this.name = name;
        this.matchOverList = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public List<MatchOver> getMatchOverList() {
        return matchOverList;
    }

    public void setMatchOverList(List<MatchOver> matchOverList) {
        this.matchOverList = matchOverList;
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

    public void addNewMatchOver(MatchOver matchOver) {
        matchOverList.add(matchOver);
    }

    public BattingStatus getBattingStatus() {
        return battingStatus;
    }

    public void setBattingStatus(BattingStatus battingStatus) {
        this.battingStatus = battingStatus;
    }
}

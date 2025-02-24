package models;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private final Integer playerId;

    public Player(Integer playerId, String name) {
        this.playerId = playerId;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPlayerId() {
        return playerId;
    }
}

package models;

import constants.BallType;

public class PlayerRunDetail {
    private final Player player;
    private final BallType ballType;
    private int count;

    public PlayerRunDetail(Player player, BallType ballType) {
        this.player = player;
        this.ballType = ballType;
        this.count = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public BallType getBallType() {
        return ballType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

package models;

import constants.BallType;

public class PlayerRunDetail {
    private  final Player player;
    private final BallType ballType;
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

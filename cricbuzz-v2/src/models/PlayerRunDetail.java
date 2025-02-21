package models;

import constants.BallType;

public class PlayerRunDetail  {
    private final Player player;
    private final BallType ballType;
    private Integer count;

    public PlayerRunDetail(Player player, BallType ballType, Integer count) {
        this.player = player;
        this.ballType = ballType;
        this.count = count;
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

    @Override
    public String toString() {
        return "PlayerRunDetail{" +
                "player=" + player +
                ", ballType=" + ballType +
                ", count=" + count +
                '}';
    }
}

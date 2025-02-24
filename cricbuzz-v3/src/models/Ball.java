package models;

import constants.BallType;

public class Ball {
    private final Player batsman;
    private final BallType ballType;

    public Ball(Player batsman, BallType ballType) {
        this.batsman = batsman;
        this.ballType = ballType;
    }

    public Player getBatsman() {
        return batsman;
    }

    public BallType getBallType() {
        return ballType;
    }
}

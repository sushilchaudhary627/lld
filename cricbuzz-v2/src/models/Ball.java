package models;

import constants.BallType;

public class Ball {
    private final BallType ballType;
    private final Player batsman;

    public Ball(BallType ballType, Player batsman) {
        this.ballType = ballType;
        this.batsman = batsman;

    }

    public BallType getBallType() {
        return ballType;
    }

    public Player getBatsman() {
        return batsman;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "ballType=" + ballType +
                ", batsman=" + batsman +
                '}';
    }
}

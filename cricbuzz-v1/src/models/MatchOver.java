package models;

import java.util.ArrayList;
import java.util.List;

public class MatchOver {
    private final Integer overNo;
    private List<Ball> balls;

    public MatchOver(Integer overNo) {
        this.overNo = overNo;
        this.balls = new ArrayList<>();
    }

    public void addNewBall(Ball ball){
        balls.add(ball);
    }

    public Integer getOverNo() {
        return overNo;
    }

    public List<Ball> getBalls() {
        return balls;
    }
}

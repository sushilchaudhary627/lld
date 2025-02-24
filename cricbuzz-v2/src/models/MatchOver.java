package models;

import java.util.ArrayList;
import java.util.List;

public class MatchOver {
    private final Integer matchOverNo;
    private List<Ball> balls;


    public MatchOver(Integer matchOverNo) {
        this.matchOverNo = matchOverNo;
        this.balls = new ArrayList<>();
    }

    public Integer getMatchOverNo() {
        return matchOverNo;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    public void setBalls(List<Ball> balls) {
        this.balls = balls;
    }

    public void addNewBall(Ball ball){
        balls.add(ball);
    }
}

package models;

import java.util.ArrayList;
import java.util.List;

public class MatchOver {
    private final Integer matchOverNumber;
    private List<Ball> balls;


    public MatchOver(Integer matchOverNumber) {
        this.matchOverNumber = matchOverNumber;
        this.balls = new ArrayList<>();
    }

    public void addNewBall(Ball ball){
        balls.add(ball);
    }

    public Integer getMatchOverNumber() {
        return matchOverNumber;
    }

    public List<Ball> getBalls() {
        return balls;
    }

    @Override
    public String toString() {
        return "MatchOver{" +
                "matchOverNumber=" + matchOverNumber+
                '}';
    }
}

package services;

import constants.BallType;
import dto.PlayerScore;
import dto.ScoreResponse;
import dto.TeamScore;
import models.Ball;
import models.MatchOver;
import models.Player;
import models.Team;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScoreService {
    public ScoreResponse getScoreForOver(Team team){
        HashMap<String, Integer>sixes = new HashMap<>();
        HashMap<String, Integer>fours = new HashMap<>();
        HashMap<String, Integer>runs = new HashMap<>();
        HashMap<String, Integer>ballFaced = new HashMap<>();
        int wicketCount = 0;
        int totalScore = 0;

       for(MatchOver matchOver:team.getMatchOvers()){
         for(Ball ball:matchOver.getBalls()){
             totalScore = totalScore + ball.getBallType().getScore();
             if(Objects.equals(BallType.WICKET, ball.getBallType())){
                 wicketCount = wicketCount+1;
             }
             String name = ball.getBatsman().getName();
             if(Objects.equals(BallType.SIX, ball.getBallType())){
                  sixes.put(name, sixes.getOrDefault(name, 0)+1);
             }

             if(Objects.equals(BallType.FOUR, ball.getBallType())){
                 fours.put(name, fours.getOrDefault(name, 0)+1);
             }
             runs.put(name, runs.getOrDefault(name, 0)+ball.getBallType().getScore());
             ballFaced.put(name, ballFaced.getOrDefault(name, 0)+1);
         }}
       Map<String, PlayerScore>playerScoreMap = new HashMap<>();
        for(Player player:team.getPlayers()){
            String name = player.getName();
            PlayerScore playerScore = new PlayerScore(name, sixes.getOrDefault(name, 0),fours.getOrDefault(name, 0), ballFaced.getOrDefault(name, 0), runs.getOrDefault(name, 0));
            playerScoreMap.put(name, playerScore);
            System.out.println(playerScore);
        }
        return new ScoreResponse(new TeamScore(team.getName(),totalScore, wicketCount, team.getMatchOvers().size()), playerScoreMap);
    }
}

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
    public ScoreResponse getScoreForOver(Team team) {
        HashMap<String, Integer> sixes = new HashMap<>();
        HashMap<String, Integer> fours = new HashMap<>();
        HashMap<String, Integer> runs = new HashMap<>();
        HashMap<String, Integer> ballFaced = new HashMap<>();
        int wicketCount = 0;
        int totalScore = 0;

        for (MatchOver matchOver : team.getMatchOvers()) {
            for (Ball ball : matchOver.getBalls()) {
                // Handle total score correctly (including WIDE and NO BALL)
                if (ball.getBallType().isExtra()) {
                    totalScore += ball.getBallType().getScore() + 1; // Extra run for extra delivery
                } else {
                    totalScore += ball.getBallType().getScore();
                }

                if (ball.getBallType() == BallType.WICKET) {
                    wicketCount++;
                }

                String name = ball.getBatsman().getName();
                if (ball.getBallType() == BallType.SIX) {
                    sixes.put(name, sixes.getOrDefault(name, 0) + 1);
                }

                if (ball.getBallType() == BallType.FOUR) {
                    fours.put(name, fours.getOrDefault(name, 0) + 1);
                }

                runs.put(name, runs.getOrDefault(name, 0) + ball.getBallType().getScore());

                // Only count faced balls if it’s NOT a WIDE ball
                if (ball.getBallType() != BallType.WIDE) {
                    ballFaced.put(name, ballFaced.getOrDefault(name, 0) + 1);
                }
            }
        }

        Map<String, PlayerScore> playerScoreMap = new HashMap<>();
        for (Player player : team.getPlayers()) {
            String name = player.getName();
            PlayerScore playerScore = new PlayerScore(
                    name,
                    sixes.getOrDefault(name, 0),
                    fours.getOrDefault(name, 0),
                    ballFaced.getOrDefault(name, 0),
                    runs.getOrDefault(name, 0)
            );
            playerScoreMap.put(name, playerScore);
            System.out.println(playerScore);

            // Log players who didn't bat
            if (ballFaced.getOrDefault(name, 0) == 0) {
                System.out.println(name + " did not face any balls.");
            }
        }

        return new ScoreResponse(new TeamScore(team.getName(), totalScore, wicketCount, team.getMatchOvers().size()), playerScoreMap);
    }
}

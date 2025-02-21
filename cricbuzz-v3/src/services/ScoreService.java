package services;

import constants.BallType;
import dto.PlayerScore;
import models.Ball;
import models.Player;
import models.PlayerRunDetail;
import models.Team;
import repo.ScoreRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreService {
    private final ScoreRepo scoreRepo;

    public ScoreService(ScoreRepo scoreRepo) {
        this.scoreRepo = scoreRepo;
    }

    public void updateMatchScore(Team team, Ball ball) {
        BallType ballType = ball.getBallType();
        Player batsman = ball.getBatsman();
        PlayerRunDetail playerRunDetail = scoreRepo.findRuns(batsman).stream().filter(s -> s.getBallType() == ball.getBallType()).findFirst().orElse(new PlayerRunDetail(batsman, ball.getBallType()));
        playerRunDetail.setCount(playerRunDetail.getCount() + 1);
        if (playerRunDetail.getCount() == 1) {
            scoreRepo.save(playerRunDetail);
        } else {
            scoreRepo.update(playerRunDetail);
        }
    }


    public Integer getScoreOfTeam(Team team) {
        int runs = 0;
        for (Player player : team.getPlayers()) {
            runs = runs + scoreRepo.findRuns(player).stream().mapToInt(r -> r.getCount() * r.getBallType().getScore()).sum();
        }
        return runs;
    }

    public List<PlayerScore> getScorePlayerWise(Team team) {
        List<PlayerScore> playerScores = new ArrayList<>();
        for (Player player : team.getPlayers()) {
            PlayerScore playerScore = new PlayerScore(player.getName());
            List<PlayerRunDetail> playerRunDetails = scoreRepo.findRuns(player);
            Optional<PlayerRunDetail> sixRunDetail = playerRunDetails.stream().filter(r -> r.getBallType() == BallType.SIX).findFirst();
            sixRunDetail.ifPresent(playerRunDetail -> playerScore.setSixes(playerRunDetail.getCount()));

            Optional<PlayerRunDetail> fourRunDetail = playerRunDetails.stream().filter(r -> r.getBallType() == BallType.FOUR).findFirst();
            fourRunDetail.ifPresent(playerRunDetail -> playerScore.setFours(playerRunDetail.getCount()));

            int totalBallFaced = playerRunDetails.stream().mapToInt(r -> r.getCount()).sum();
            int runs = playerRunDetails.stream().mapToInt(r -> BallType.isExtraRun(r.getBallType()) ? 0 : r.getCount() * r.getBallType().getScore()).sum();
            playerScore.setBallFaced(totalBallFaced);
            playerScore.setRuns(runs);
            playerScores.add(playerScore);
        }
        return playerScores;
    }
}

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
import java.util.stream.Collectors;

public class ScoreService {
    private final ScoreRepo scoreRepo;

    public ScoreService(ScoreRepo scoreRepo) {
        this.scoreRepo = scoreRepo;
    }

    public void updatePlayerScore(Team team, Ball ball) {
        Player player = ball.getBatsman();
        List<PlayerRunDetail> runDetailList = scoreRepo.getPlayerRuns(player);
        PlayerRunDetail playerRunDetail = runDetailList.stream().filter(r -> (r.getBallType() == ball.getBallType())).findFirst().orElse(
                new PlayerRunDetail(player, ball.getBallType(), 0)
        );
        playerRunDetail.setCount(playerRunDetail.getCount()+1);
        if(playerRunDetail.getCount() == 1){
        scoreRepo.save(playerRunDetail);}
        else{
            scoreRepo.update(playerRunDetail);
        }
        System.out.println("request received for updating " + playerRunDetail);
    }

    public Integer getTotalRun(Team team){
        Integer runs = 0;
        for(Player player:team.getPlayers()){
            List<PlayerRunDetail> runDetailList = scoreRepo.getPlayerRuns(player);
            runs = runs + runDetailList.stream().mapToInt(r -> r.getCount()*r.getBallType().getScore()).sum();
        }
        return runs;
    }

    public List<PlayerScore>  getPlayerScore(Team team){
        Integer runs = 0;
        List<PlayerScore>playerScores = new ArrayList<>();
        for(Player player:team.getPlayers()){
            List<PlayerRunDetail> runDetailList = scoreRepo.getPlayerRuns(player);
            Integer ballfaced = runDetailList.stream().mapToInt(r -> r.getCount()).sum();
            runs = runDetailList.stream().mapToInt(r -> r.getCount()*r.getBallType().getScore()).sum();
            PlayerRunDetail sixRunDetail  = runDetailList.stream().filter( r -> r.getBallType() == BallType.SIX).findFirst().orElse(null);
            PlayerRunDetail  fourRunDetail  = runDetailList.stream().filter( r -> r.getBallType() == BallType.FOUR).findFirst().orElse(null);
            PlayerScore playerScore = new PlayerScore(player.getName(), runs,  sixRunDetail != null ? sixRunDetail.getCount(): 0, fourRunDetail!= null ? fourRunDetail.getCount() :0, ballfaced);
            playerScores.add(playerScore);
        }
        return playerScores;
    }
}

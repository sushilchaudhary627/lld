package services;

import constants.BallType;
import dto.PlayerScoreDetail;
import dto.TeamScoreCard;
import models.Ball;
import models.Player;
import models.PlayerRunDetail;
import models.Team;
import repo.PlayerRunRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ScoreService {
    private final PlayerRunRepo playerRunRepo;

    public ScoreService(PlayerRunRepo playerRunRepo) {
        this.playerRunRepo = playerRunRepo;
    }

    public void updatePlayerScore(Ball ball){
        Player batsman = ball.getBatsman();
        BallType ballType = ball.getBallType();
        List<PlayerRunDetail> playerRunDetailList = playerRunRepo.findRunsFor(batsman);
        playerRunDetailList.forEach(playerRunDetail -> {
            if(playerRunDetail.getBallType() == ballType){
                playerRunDetail.setCount(playerRunDetail.getCount()+1);
                playerRunRepo.update(playerRunDetail);
            }
        });
        if(!playerRunDetailList.stream().anyMatch( r ->r.getBallType() == ballType)){
            PlayerRunDetail playerRunDetail = new PlayerRunDetail(batsman, ballType);
            playerRunDetail.setCount(1);
            playerRunRepo.save(playerRunDetail);
        }
    }

    public Integer getTotalRunForTeam(Team team){
        return team.getPlayers().stream().map(
            p ->playerRunRepo.findRunsFor(p).stream().mapToInt( r ->r.getBallType().getRuns()*r.getCount()).sum()
        ).mapToInt(o -> o).sum();
    }

    public TeamScoreCard getScoreCardForTeam(Team team){
        List<PlayerScoreDetail>playerScoreDetails = new ArrayList<>();
        AtomicInteger wicket = new AtomicInteger();
        for(Player player:team.getPlayers()){
            List<PlayerRunDetail> playerRunDetails = playerRunRepo.findRunsFor(player);
            PlayerScoreDetail playerScoreDetail = new PlayerScoreDetail();
            playerScoreDetail.setName(player.getName());
            AtomicInteger totalRun = new AtomicInteger();
            AtomicInteger totalBallFaced = new AtomicInteger();
            playerRunDetails.forEach(playerRunDetail -> {
                if(playerRunDetail.getBallType() == BallType.SIX){
                    playerScoreDetail.setSixes(playerRunDetail.getCount());
                }

            if(playerRunDetail.getBallType() == BallType.FOUR){
                playerScoreDetail.setFours(playerRunDetail.getCount());

            }
            if(!BallType.isExtra(playerRunDetail.getBallType())){
                totalRun.set(totalRun.get() + playerRunDetail.getBallType().getRuns()*playerRunDetail.getCount());
                totalBallFaced.set(totalBallFaced.get() + 1);
            }
            if(playerRunDetail.getBallType() == BallType.WICKET){
                wicket.getAndIncrement();
            }
            });
            playerScoreDetail.setRuns(totalRun.get());
            playerScoreDetail.setBallFaced(totalBallFaced.get());
            playerScoreDetails.add(playerScoreDetail);
        }
        TeamScoreCard teamScoreCard= new TeamScoreCard();
        teamScoreCard.setName(team.getName());
        teamScoreCard.setPlayerScoreDetaillist(playerScoreDetails);
        teamScoreCard.setTotalRuns(getTotalRunForTeam(team));
        teamScoreCard.setWicketCount(wicket.get());
        return teamScoreCard;
    }
}

package services;

import constants.BallType;
import models.Ball;
import models.MatchOver;
import models.Player;
import models.Team;
import repo.TeamRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TeamService {
    private final AtomicInteger teamIdGen;
    private final TeamRepo teamRepo;
    private final PlayerService playerService;

    public TeamService(AtomicInteger teamIdGen, TeamRepo teamRepo, PlayerService playerService) {
        this.teamIdGen = teamIdGen;
        this.teamRepo = teamRepo;
        this.playerService = playerService;
    }
    
    public Team registerTeam(String name, List<Integer> playerIds){
        validateRegisterTeamReq(name, playerIds);
        List<Player>playerList = playerService.getPlayers(playerIds);
        if(playerList.size() != playerIds.size()){
            throw new RuntimeException("Some player not found in our system.");
        }
        Team team = new Team(teamIdGen.getAndIncrement(), name);
        team.setPlayers(playerList);
        team.setStriker(playerList.get(0));
        team.setNonStriker(playerList.get(1));
        team.setPlayersAvailable(new ArrayList<>(playerList));
        team.getPlayersAvailable().remove(1);
        team.getPlayersAvailable().remove(0);
        teamRepo.save(team);
        System.out.println("team is created ");
        System.out.println(team);
        return team;
    }

    private void validateRegisterTeamReq(String name, List<Integer> playerIds) {
    }


    public List<Team> getTeams(List<Integer> teamIds) {
        return teamRepo.getTeams(teamIds);
    }

    public void updateMatchScore(Integer teamId, Integer overNo, BallType ballType){
        Team team = teamRepo.getTeamById(teamId).orElseThrow();
        Ball ball = new Ball(ballType, team.getStriker());
        MatchOver matchOver;
        Boolean newOverStarted= false;
        if(team.getMatchOvers().isEmpty()){
            matchOver = new MatchOver(overNo);
            team.addNewMatchOver(matchOver);
        } else{
            matchOver = team.getMatchOvers().getLast();
            if(!Objects.equals(matchOver.getOverNo(), overNo)){
                matchOver = new MatchOver(overNo);
                team.addNewMatchOver(matchOver);
                newOverStarted = true;
            }
        }
        matchOver.addNewBall(ball);
        updateStrikerAndNonStriker(team, ballType, newOverStarted);
        teamRepo.update(team);
    }

    private boolean checkPlayerIsOut(BallType ballType){
        return Objects.equals(ballType, BallType.WICKET);
    }

    private void updateStrikerAndNonStriker(Team team, BallType ballType, Boolean newOverStarted){
        Boolean playerIsOut = checkPlayerIsOut(ballType);
        if(playerIsOut){
            team.setStriker(getNewStriker(team));
        }else{
            if(newOverStarted || (ballType.getScore()%2 == 1 && !Objects.equals(ballType, BallType.WIDE))){
                Player striker = team.getStriker();
                team.setStriker(team.getNonStriker());
                team.setNonStriker(striker);
            }
        }
    }

    public Player getNewStriker(Team team){
        if(team.getPlayersAvailable().isEmpty())
        { throw new RuntimeException("No batsman left for batting");}
        Player player = team.getPlayersAvailable().getFirst();
        team.getPlayersAvailable().remove(player);
        return player;

    }
}

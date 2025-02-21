package services;

import constants.BallType;
import constants.BattingStatus;
import models.Player;
import models.Team;
import repo.TeamRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class TeamService {
    private final AtomicLong teamIdGen;
    private final TeamRepo teamRepo;
    private final PlayerService playerService;

    public TeamService(AtomicLong teamIdGen, TeamRepo teamRepo, PlayerService playerService) {
        this.teamIdGen = teamIdGen;
        this.teamRepo = teamRepo;
        this.playerService = playerService;
    }

    public Team registerTeam(List<Long> playerIds, String name){
        validateRegisterTeamReq(playerIds, name);
        List<Player>players = playerService.findPlayers(playerIds);
        if(players.size() != playerIds.size()){
            throw new RuntimeException("Some Players not found in the system.");
        }
        Team team = new Team(teamIdGen.getAndIncrement(), name);
        team.setPlayers(players);
        team.setBattingOrder(new LinkedList<>(players));
        team.setStriker(team.getBattingOrder().poll());
        team.setNonStriker(team.getBattingOrder().poll());
        team.setBattingStatus(BattingStatus.READY);
        teamRepo.save(team);
        System.out.printf("Team: %s is registered successfully with striker:%s and non-striker %s%n", team.getName(), team.getStriker().getName(), team.getNonStriker().getName());
        return team;
    }

    private void validateRegisterTeamReq(List<Long> playerIds, String name) {
    }

    public List<Team> findTeams(List<Long> teamIds) {
        return teamRepo.findTeams(teamIds);
    }

    public Team findTeamById(Long teamId) {
        return teamRepo.findTeamById(teamId).orElseThrow();
    }

    public void updateStrikerPos(Team team, BallType ballType) {
        if(ballType == BallType.WICKET){
            if(team.getBattingOrder().isEmpty()){
                System.out.printf("All player of team: %s are out.\n", team.getName());
            }else{
                team.setStriker(team.getBattingOrder().poll());
            }
        }else{
            if(ballType.getScore() %2 == 1 && !BallType.isExtraRun(ballType)){
                 swapStrikers(team);
            }
            if(team.getMatchOverList().getLast().getBalls().stream().filter( b -> !BallType.isExtraRun(b.getBallType())).count() == 6){
                System.out.printf("Swapping striker for next over\n");
                swapStrikers(team);
            }
        }
    }

    public void swapStrikers(Team team){
        Player currentStriker = team.getStriker();
        team.setStriker(team.getNonStriker());
        team.setNonStriker(currentStriker);
    }
}

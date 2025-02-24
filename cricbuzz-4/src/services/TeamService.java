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
    private final AtomicLong teamIdGenerator;
    private final TeamRepo teamRepo;
    private final PlayerService playerService;

    public TeamService(AtomicLong teamIdGenerator, TeamRepo teamRepo, PlayerService playerService) {
        this.teamIdGenerator = teamIdGenerator;
        this.teamRepo = teamRepo;
        this.playerService = playerService;
    }

    public Team registerTeam(String teamName, List<Long> playerIds){
        validateRegisterTeamReq(teamName, playerIds);
        List<Player>players = playerService.getPlayersById(playerIds);
        if(players.size() != playerIds.size()){
            throw new RuntimeException("Some player not found in system.");
        }
        Team team = new Team(teamIdGenerator.getAndIncrement(), teamName);
        team.setPlayers(players);
        team.setBattingOrder(new LinkedList<>(players));
        team.setStriker(team.getBattingOrder().poll());
        team.setNonStriker(team.getBattingOrder().poll());
        team.setBattingStatus(BattingStatus.NOT_STARTED);
        teamRepo.save(team);
        System.out.printf("Team: %s is registered with total players: %s\n", team.getName(), team.getPlayers().size());
        return team;
    }

    private void validateRegisterTeamReq(String teamName, List<Long> playerIdS) {
    }

    public List<Team> findTeamsById(List<Long> teamIds) {
        return teamRepo.findTeamsById(teamIds);
    }

    public void updateStriker(Team team, BallType ballType) {
        if(ballType == BallType.WICKET){
            if(team.getBattingOrder().isEmpty()){
                System.out.printf("ALL OUT in team: %s\n", team.getName());
                team.setStriker(null);
                return;
            }else{
                team.setStriker(team.getBattingOrder().poll());
            }
        } else{
            if(ballType.getRuns() %2 == 1 && !BallType.isExtra(ballType)){
                  swapStriker(team);
            }

            if(team.getMatchOvers().getLast().isOverCompleted()){
                swapStriker(team);
            }
        }
        //System.out.printf("Team: %s striker: %s non-striker: %s\n", team.getName(), team.getStriker().getName(), team.getNonStriker().getName());
    }

    private void swapStriker(Team team){
        Player striker = team.getStriker();
        team.setStriker(team.getNonStriker());
        team.setNonStriker(striker);
    }
}

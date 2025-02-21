package services;

import constants.BallType;
import models.*;
import repo.TeamRepo;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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

    public Team registerTeam(List<Long> playerIds, String name){
        validateTeamRegisterReq(playerIds, name);
        List<Player>players = playerService.getPlayers(playerIds);
        if(players.size() != playerIds.size()){
            throw new RuntimeException("Some player not found in system");
        }
        Team team = new Team(teamIdGenerator.getAndIncrement(), name);
        team.setPlayers(players);
        team.setBattingOrder(new LinkedList<>(players));
        team.setStriker(team.getBattingOrder().poll());
        team.setNonStriker(team.getBattingOrder().poll());
        teamRepo.save(team);
        System.out.println("Team is created with striker: " + team.getStriker() + " and not striker: "+ team.getNonStriker());
        return team;
    }

    private void validateTeamRegisterReq(List<Long> playerIds, String name) {
        Objects.requireNonNull(playerIds, "player ids should not be null.");
        Objects.requireNonNull(name, "Team name must not be null");
        if(playerIds.size() < 2){
            throw new RuntimeException("At least two player should be present in the team");
        }
        if(name.isEmpty()){
            throw new RuntimeException("name should not be empty.");
        }
    }


    public List<Team> getTeams(List<Long> teamIds) {
        return teamRepo.getTeams(teamIds);
    }

    public Team getTeamById(long teamId) {
        return teamRepo.getById(teamId).orElseThrow();
    }

    public void changeStrikers(Team team, BallType ballType) {
        System.out.println(ballType);
        if(ballType == BallType.WICKET){
            if(team.getBattingOrder().isEmpty()){
                throw new RuntimeException("All out...");
            }
            Player player = team.getBattingOrder().poll();
            System.out.println(team.getStriker() + " replaced to new striker " + player);
            team.setStriker(player);
        }else{
            if(ballType.getScore()%2 == 1 || currentOverCompleted(team.getMatchOverList().getLast())){
                Player currentStriker = team.getStriker();
                team.setStriker(team.getNonStriker());
                team.setNonStriker(currentStriker);
                System.out.println("Strikers swapped: Now striker -> " + team.getStriker() + ", non-striker -> " + team.getNonStriker());
            }
        }
    }

    private boolean currentOverCompleted(MatchOver matchOver) {
        return matchOver.getBalls().size() == 6;
    }
}

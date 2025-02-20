package services;

import constants.BallType;
import models.Ball;
import models.MatchOver;
import models.Player;
import models.Team;
import repo.TeamRepo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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

    public Team registerTeam(String name, List<Integer> playerIds) {
        validateRegisterTeamReq(name, playerIds);
        List<Player> playerList = playerService.getPlayers(playerIds);
        if (playerList.size() != playerIds.size()) {
            throw new RuntimeException("Some player not found in our system.");
        }

        Team team = new Team(teamIdGen.getAndIncrement(), name);
        team.setPlayers(playerList);
        team.setStriker(playerList.get(0));
        team.setNonStriker(playerList.get(1));
        team.setPlayersAvailable(new LinkedList<>(playerList)); // ✅ Change to LinkedList

        // Remove first two players as they are already batting
        team.getPlayersAvailable().remove(0);
        team.getPlayersAvailable().remove(0);

        teamRepo.save(team);
        System.out.println("Team is created: " + team);
        return team;
    }

    private void validateRegisterTeamReq(String name, List<Integer> playerIds) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Team name cannot be empty.");
        }
        if (playerIds == null || playerIds.size() < 2) {
            throw new IllegalArgumentException("At least 2 players are required to form a team.");
        }
    }

    public List<Team> getTeams(List<Integer> teamIds) {
        return teamRepo.getTeams(teamIds);
    }

    public void updateMatchScore(Integer teamId, Integer overNo, BallType ballType) {
        Team team = teamRepo.getTeamById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found with ID: " + teamId));

        Ball ball = new Ball(ballType, team.getStriker());
        MatchOver matchOver;

        if (team.getMatchOvers().isEmpty()) {
            matchOver = new MatchOver(overNo);
            team.addNewMatchOver(matchOver);
        } else {
            matchOver = team.getMatchOvers().get(team.getMatchOvers().size() - 1);
            if (!Objects.equals(matchOver.getOverNo(), overNo)) {
                matchOver = new MatchOver(overNo);
                team.addNewMatchOver(matchOver);
            }
        }

        matchOver.addNewBall(ball);
        updateStrikerAndNonStriker(team, ballType);
        teamRepo.update(team);
    }

    private boolean checkPlayerIsOut(BallType ballType) {
        return ballType == BallType.WICKET;  // ✅ Use == for Enum comparison
    }

    private void updateStrikerAndNonStriker(Team team, BallType ballType) {
        boolean playerIsOut = checkPlayerIsOut(ballType);
        if (playerIsOut) {
            team.setStriker(getNewStriker(team));
        } else {
            if (isCurrentOverCompleted(team.getMatchOvers().getLast()) || (ballType.getScore() % 2 == 1 && !ballType.isExtra())) {  // ✅ Correct WIDE/NO-BALL handling
                Player striker = team.getStriker();
                team.setStriker(team.getNonStriker());
                team.setNonStriker(striker);
            }
        }
    }

    public Player getNewStriker(Team team) {
        if (team.getPlayersAvailable().isEmpty()) {
            throw new RuntimeException("No batsman left for batting");
        }
        return team.getPlayersAvailable().remove(0);  // ✅ Use remove(0) for ArrayList
    }

    Boolean isCurrentOverCompleted(MatchOver matchOver){
        return matchOver.getBalls().size()== 6;
    }
}

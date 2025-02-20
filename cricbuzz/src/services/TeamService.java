package services;

import models.Player;
import models.Team;
import repository.TeamRepository;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class TeamService {
    private final AtomicInteger teamIdGenerator;
    private final PlayerService playerService;
    private final TeamRepository teamRepository;

    public TeamService(AtomicInteger teamIdGenerator, PlayerService playerService, TeamRepository teamRepository) {
        this.teamIdGenerator = teamIdGenerator;
        this.playerService = playerService;
        this.teamRepository = teamRepository;
    }

    public Team createTeam(String teamName, List<Integer> playerIds){
        List<Player> players = playerService.getPlayers(playerIds);
        Integer teamId = teamIdGenerator.getAndIncrement();
        Team team = new Team(teamId, teamName, players);
        team.setStriker(players.get(0));
        team.setNonStriker(players.get(1));
        teamRepository.save(team);
        return team;
    }

    public Team getTeamById(Integer teamId){
        return teamRepository.getTeamById(teamId).orElseThrow();
    }
}

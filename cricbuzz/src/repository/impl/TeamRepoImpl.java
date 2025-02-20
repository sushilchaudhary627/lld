package repository.impl;

import models.Team;
import repository.TeamRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TeamRepoImpl implements TeamRepository {
    Map<Integer, Team> teamMap = new HashMap<>();
    @Override
    public void save(Team team) {
        teamMap.put(team.getTeamId(), team);
    }

    @Override
    public Optional<Team> getTeamById(Integer teamId) {
        return Optional.ofNullable(teamMap.get(teamId));
    }
}

package repo.impl;

import models.Team;
import repo.TeamRepo;

import java.util.*;
import java.util.stream.Collectors;

public class TeamRepoImpl implements TeamRepo {
    Map<Long, Team> teamMap = new HashMap<>();
    @Override
    public void save(Team team) {
        teamMap.put(team.getId(), team);
    }

    @Override
    public Optional<Team> findTeamById(Long id) {
        return Optional.ofNullable(teamMap.get(id));
    }

    @Override
    public List<Team> findTeams(List<Long> teamIds) {
        return teamIds.stream().map(id -> teamMap.get(id)).filter(Objects::nonNull).collect(Collectors.toList());
    }
}

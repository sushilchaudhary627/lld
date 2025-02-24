package repo.impl;

import models.Team;
import repo.TeamRepo;

import java.util.*;

public class TeamRepoImpl implements TeamRepo {
    private final Map<Long, Team> teamMap = new HashMap<>();
    @Override
    public void save(Team team) {
        teamMap.put(team.getId(), team);
    }

    @Override
    public Optional<Team> getById(Long id) {
        return Optional.ofNullable(teamMap.get(id));
    }

    @Override
    public List<Team> getTeams(List<Long> teamIds) {
        List<Team>teams = new ArrayList<>();
        for(Long teamId:teamIds){
            if(teamMap.containsKey(teamId)){
                teams.add(teamMap.get(teamId));
            }
        }
        return teams;
    }
}

package repo.impl;

import models.Team;
import repo.TeamRepo;

import java.util.*;

public class TeamRepoImpl implements TeamRepo {
    Map<Integer,Team>teamMap = new HashMap<>();
    @Override
    public void save(Team team) {
        teamMap.put(team.getId(), team);
    }

    @Override
    public Optional<Team> getTeamById(Integer team) {
        return Optional.ofNullable(teamMap.get(team));
    }

    @Override
    public void update(Team team) {

    }

    @Override
    public List<Team> getTeams(List<Integer> teamIds) {
         List<Team>teams = new ArrayList<>();
         for(Integer id:teamIds){
             if(teamMap.containsKey(id)){
                 teams.add(teamMap.get(id));
             }
         }

        return teams;
    }
}

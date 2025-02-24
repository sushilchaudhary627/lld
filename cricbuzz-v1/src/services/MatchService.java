package services;

import models.Match;
import models.Team;
import repo.MatchRepo;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MatchService {
    private final AtomicInteger teamIdGen ;
    private final MatchRepo matchRepo;
    private final TeamService teamService;

    public MatchService(AtomicInteger teamIdGen, MatchRepo matchRepo, TeamService teamService) {
        this.teamIdGen = teamIdGen;
        this.matchRepo = matchRepo;
        this.teamService = teamService;
    }

    public Match createMatch(String name, List<Integer> teamIds){
        validateCreateMatchReq(name, teamIds);
        List<Team>teams = teamService.getTeams(teamIds);
        Match match = new Match(teamIdGen.getAndIncrement(), name);
        match.setTeams(teams);
        matchRepo.save(match);
        System.out.println("match is created:" + match);
        return match;
    }

    private void validateCreateMatchReq(String name, List<Integer> teamIds) {
    }


}

package services;

import constants.BallType;
import models.Ball;
import models.Match;
import models.MatchOver;
import models.Team;
import repo.MatchRepo;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class MatchService {
    private final AtomicLong matchIdGen;
    private final MatchRepo matchRepo;
    private final TeamService teamService;
    private final ScoreService scoreService;
    public MatchService(AtomicLong matchIdGen, MatchRepo matchRepo, TeamService teamService, ScoreService scoreService) {
        this.matchIdGen = matchIdGen;
        this.matchRepo = matchRepo;
        this.teamService = teamService;
        this.scoreService = scoreService;
    }

    public Match createMatch(List<Long> teamIds, String matchName){
        validateMatchCreateReq(teamIds, matchName);
        List<Team>teams = teamService.getTeams(teamIds);
        if(teams.size() != teamIds.size()){
            throw new RuntimeException("some team not found");
        }
        Match match = new Match(matchIdGen.getAndIncrement());
        match.setTeams(teams);
        match.setMatchName(matchName);
        matchRepo.save(match);
        return match;
    }

    private void validateMatchCreateReq(List<Long> teamIds, String matchName) {
    }

    public void updateMatchScore(long teamId, Integer matchOverNo, BallType ballType){
        Team team = teamService.getTeamById(teamId);
        MatchOver matchOver;
        if(team.getMatchOverList().isEmpty()){
            matchOver = new MatchOver(matchOverNo);
            team.addNewMatchOver(matchOver);
        }else{
            matchOver = team.getMatchOverList().getLast();
            if(!Objects.equals(matchOver.getMatchOverNo(), matchOverNo)){
                matchOver = new MatchOver(matchOverNo);
                team.addNewMatchOver(matchOver);
            }
        }
        Ball ball = new Ball(ballType, team.getStriker());
        matchOver.addNewBall(ball);
        teamService.changeStrikers(team, ballType);
        scoreService.updatePlayerScore(team, ball);
    }
}

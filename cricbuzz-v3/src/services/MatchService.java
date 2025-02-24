package services;

import constants.BallType;
import constants.BattingStatus;
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

    public Match createMatch(List<Long> teamIds, String matchName, Integer totalOvers) {
        validateCreateMatchReq(teamIds, matchName);
        List<Team> teams = teamService.findTeams(teamIds);
        if (teams.size() != teamIds.size()) {
            throw new RuntimeException("Some team not found..");
        }
        Match match = new Match(matchIdGen.getAndIncrement(), matchName);
        match.setTeams(teams);
        match.setTotalOvers(totalOvers);
        matchRepo.save(match);
        System.out.printf("Match: %s is successfully registered between teams %s and %s", match.getName(), match.getTeams().get(0).getName(), match.getTeams().get(1).getName());
        return match;
    }

    private void validateCreateMatchReq(List<Long> teamIds, String matchName) {
    }

    public Match processMatchUpdate(Long matchId, Long teamId, BallType ballType, Integer overNo) {
        Match match = matchRepo.findMatchById(matchId).orElseThrow();
        Team team = teamService.findTeamById(teamId);
        MatchOver matchOver;
        Ball ball = new Ball(team.getStriker(), ballType);
        System.out.printf("Current batsman is %s  and run made by batsman %s\n", team.getStriker().getName(), ballType);
        if (team.getMatchOverList().isEmpty()) {
            matchOver = new MatchOver(overNo);
            team.addNewMatchOver(matchOver);
            team.setBattingStatus(BattingStatus.COMPLETED);
        } else {
            matchOver = team.getMatchOverList().getLast();
            if (!matchOver.getMatchOverNo().equals(overNo)) {
                matchOver = new MatchOver(overNo);
                team.addNewMatchOver(matchOver);
            }
        }
        matchOver.addNewBall(ball);
        System.out.printf("Ball: %s added to match over %s \n", ball.getBallType(), matchOver.getMatchOverNo());
        if (checkBattingCompleted(team, match.getTotalOvers())) {
            team.setBattingStatus(BattingStatus.COMPLETED);
        }
        teamService.updateStrikerPos(team, ballType);
        scoreService.updateMatchScore(team, ball);
        if (isBattingCompletedByTeams(match.getTeams())) {
            Integer scoreByTeamA = scoreService.getScoreOfTeam(match.getTeams().getFirst());
            Integer scoreByTeamB = scoreService.getScoreOfTeam(match.getTeams().getLast());
            if (scoreByTeamB > scoreByTeamA) {
                match.setWinner(match.getTeams().getLast());
            } else {
                match.setWinner(match.getTeams().getFirst());
            }
        }
        return match;
    }

    private boolean checkBattingCompleted(Team team, Integer totalOver) {
        if (Objects.isNull(team.getStriker())) {
            // all out ?
            return true;
        }
        return team.getMatchOverList().size() == totalOver && team.getMatchOverList().getLast().getBalls().size() == 6;
    }

    private boolean isBattingCompletedByTeams(List<Team> teams) {
        return teams.stream().allMatch(t ->t.getBattingStatus() == BattingStatus.COMPLETED);
    }
}

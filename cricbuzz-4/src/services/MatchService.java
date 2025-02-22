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
    private final AtomicLong matchIdGenerator;
    private final MatchRepo matchRepo;
    private final TeamService teamService;
    private final ScoreService scoreService;

    public MatchService(AtomicLong matchIdGenerator, MatchRepo matchRepo, TeamService teamService, ScoreService scoreService) {
        this.matchIdGenerator = matchIdGenerator;
        this.matchRepo = matchRepo;
        this.teamService = teamService;
        this.scoreService = scoreService;
    }

    public Match createMatch(String matchName, Integer totalMatchOver,List<Long> teamIds){
        validateCreateMatchReq(matchName, teamIds);
        List<Team>teams = teamService.findTeamsById(teamIds);
        Match match = new Match(matchIdGenerator.getAndIncrement(), totalMatchOver);
        match.setMatchName(matchName);
        match.setTeams(teams);
        matchRepo.save(match);
        System.out.printf("Match is scheduled between %s and %s teams\n", match.getTeams().getFirst().getName(),  match.getTeams().getLast().getName());
        return match;
    }

    private void validateCreateMatchReq(String matchName, List<Long> teamIds) {
        Objects.requireNonNull(matchName, "match name is required.");
        if(matchName.isEmpty()){
            throw new RuntimeException("Match name should not be empty.");
        }

    }

    public void processMatchUpdate(Long matchId, Long teamId, BallType ballType){
        Match match = matchRepo.findMatchById(matchId).orElseThrow();
        Team team = match.getTeams().stream().filter(t -> t.getId().equals(teamId)).findFirst().orElseThrow();
        Ball ball = new Ball(ballType, team.getStriker());
        System.out.printf("Batsman: %s made run : %s\n", ball.getBatsman().getName(), ballType.getValue());
        updateMatchOver(team, ball);
        teamService.updateStriker(team, ballType);
        scoreService.updatePlayerScore(ball);
        if(team.getStriker() == null || team.getMatchOvers().size() == match.getTotalMatchOver() && team.getMatchOvers().getLast().isOverCompleted()){
            team.setBattingStatus(BattingStatus.COMPLETED);
        }
        if(match.getTeams().stream().allMatch( t ->t.getBattingStatus() == BattingStatus.COMPLETED)){
            Team team1 = match.getTeams().getFirst();
            Team team2 = match.getTeams().getLast();
            if(scoreService.getTotalRunForTeam(team1) > scoreService.getTotalRunForTeam(team2)){
                match.setWinner(team1);
            }else {
                match.setWinner(team2);
            }
            System.out.printf("Match: %s is won by team: %s\n", match.getMatchName(), match.getWinner().getName());
        }
    }

    private void updateMatchOver(Team team, Ball ball) {
        MatchOver matchOver;
        if(team.getMatchOvers().isEmpty()){
            team.setBattingStatus(BattingStatus.RUNNING);
            System.out.print("first over started\n");
            matchOver = new MatchOver(1);
            team.addNewMatchOver(matchOver);
        }else{
            matchOver = team.getMatchOvers().getLast();
            if(matchOver.isOverCompleted()){
                matchOver = new MatchOver(matchOver.getMatchOverNo()+1);
                System.out.printf("new over: %s started\n", matchOver.getMatchOverNo());
                team.addNewMatchOver(matchOver);
            }
        }
        matchOver.addNewBall(ball);
    }

}

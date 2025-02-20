package services;

import constants.BallType;
import models.*;
import repository.MatchRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class CricketMatchService {
    private final AtomicInteger matchIdGenerator;
    private final TeamService teamService;
    private final MatchRepository matchRepository;

    public CricketMatchService(AtomicInteger matchIdGenerator, TeamService teamService, MatchRepository matchRepository) {
        this.matchIdGenerator = matchIdGenerator;
        this.teamService = teamService;
        this.matchRepository = matchRepository;
    }

    public Match createMatch(Integer teamAId, Integer teamBId, Integer totalOvers){
        Integer matchId = matchIdGenerator.getAndIncrement();
        Match match = new Match(matchId, totalOvers);
        match.setTeamA(teamService.getTeamById(teamAId));
        match.setTeamB(teamService.getTeamById(teamBId));
        matchRepository.save(match);
        return match;
    }

    public void ballUpdates(Integer matchOverNo, Integer teamId, BallType ballType){
        Team team = teamService.getTeamById(teamId);
        Player striker = team.getStriker();
        Player nonStriker = team.getNonStriker();
        Ball ball = new Ball(ballType, striker);
        Boolean currentPlayerOut = isCurrentPlayerOut(ballType);
        Boolean isNewOver = false;
        MatchOver currentMatchOver;
        if(team.getMatchOvers().isEmpty()){
            currentMatchOver = new MatchOver(matchOverNo);
            team.addMatchOver(currentMatchOver);
        } else {
            MatchOver currOver = team.getMatchOvers().getLast();
            if(!Objects.equals(currOver.getMatchOverNumber(), matchOverNo)){
                team.addMatchOver(new MatchOver(matchOverNo));;
                isNewOver = true;
            }
        }
        team.getMatchOvers().getLast().addNewBall(ball);
        if(currentPlayerOut){
            team.setStriker(getNewStriker(team));
        }else{
            if(isNewOver || swapBatsMan(ballType)){
                team.setNonStriker(striker);
                team.setStriker(nonStriker);
            }
        }
        System.out.println(team.getStriker() + "," + team.getNonStriker());
    }

    private Boolean isCurrentPlayerOut(BallType ballType){
        return Objects.equals(BallType.WICKET, ballType);
    }

    private Player getNewStriker(Team team){
        for(Player player:team.getPlayers()){
            if(!(Objects.equals(player.getPlayerId(), team.getStriker().getPlayerId()) || (Objects.equals(player.getPlayerId(), team.getNonStriker().getPlayerId())))){
                return player;
            }
        }
        return null;
    }

    private Boolean swapBatsMan(BallType ballType){
        return List.of(BallType.ONE, BallType.FIVE).contains(ballType);
    }

    public Map<String, String> getScore(int overNo, int teamId){
        Team team = teamService.getTeamById(teamId);
        MatchOver matchOver = team.getMatchOvers().stream().filter( over -> over.getMatchOverNumber().equals(overNo)).findFirst().orElseThrow();
        Map<String, Integer>scores = new HashMap<>();
        Map<String, Integer>ballsfaced = new HashMap<>();
        Map<String, Integer>fours= new HashMap<>();
        Map<String, Integer>sixes = new HashMap<>();
        Map<String, String>response = new HashMap<>();
        for(Ball ball: matchOver.getBalls()){
            String batsmanName = ball.getBatsman().getName();
            scores.put(batsmanName, scores.getOrDefault(batsmanName, 0) + ball.getBallType().getPositiveScore());
            ballsfaced.put(batsmanName, ballsfaced.getOrDefault(batsmanName, 0)+1);
            if(Objects.equals(BallType.SIXES, ball.getBallType())){

                sixes.put(batsmanName, sixes.getOrDefault(batsmanName, 0)+1);
            }
            if(Objects.equals(BallType.FOUR, ball.getBallType())){
                fours.put(batsmanName, fours.getOrDefault(batsmanName, 0)+1);
            }
        }
        for(Player player:team.getPlayers()){
            String name = player.getName();
            String value = scores.getOrDefault(name, 0) + " " + fours.getOrDefault(name, 0) + " " + sixes.getOrDefault(name, 0) + " "+ ballsfaced.getOrDefault(name, 0);
            response.put(name, value);
        }
        return response;
    }
}

import constants.BallType;
import models.*;
import repository.MatchRepository;
import repository.PlayerRepository;
import repository.TeamRepository;
import repository.impl.MatchRepoImpl;
import repository.impl.PlayerRepoImpl;
import repository.impl.TeamRepoImpl;
import services.CricketMatchService;
import services.PlayerService;
import services.TeamService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TeamRepository teamRepository = new TeamRepoImpl();
        PlayerRepository playerRepository = new PlayerRepoImpl();
        MatchRepository matchRepository = new MatchRepoImpl();
        PlayerService playerService = new PlayerService(new AtomicInteger(1), playerRepository);
        TeamService teamService = new TeamService(new AtomicInteger(1), playerService, teamRepository);
        CricketMatchService cricketMatchService = new CricketMatchService(new AtomicInteger(1), teamService, matchRepository);
        for(Integer i=1; i<=10; i++){
            playerService.createPlayer("player" +i.toString());
        }

        Team team1= teamService.createTeam("dhum dhum", List.of(1,2,3,4,5));
        Team team2 = teamService.createTeam("dhum dhum", List.of(6,7,8,9,10));
        Match match = cricketMatchService.createMatch(1,2,10);
        cricketMatchService.ballUpdates(1, 1, BallType.ONE);
        cricketMatchService.ballUpdates(1, 1, BallType.ONE);
        cricketMatchService.ballUpdates(1, 1, BallType.ONE);
        cricketMatchService.ballUpdates(1, 1, BallType.ONE);
        cricketMatchService.ballUpdates(1, 1, BallType.ONE);
        cricketMatchService.ballUpdates(1, 1, BallType.TWO);
        for(MatchOver matchOver: team1.getMatchOvers()){
            for(Ball ball:matchOver.getBalls()){
                System.out.println(ball);
            }
        }
        System.out.println(cricketMatchService.getScore(1,1));

    }
}


// 10 min problem understanding
// 15 min basic model writing without test
// total time - 4:30 started and ended at 6:45
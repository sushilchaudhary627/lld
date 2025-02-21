import constants.BallType;
import repo.MatchRepo;
import repo.PlayerRepo;
import repo.ScoreRepo;
import repo.TeamRepo;
import repo.impl.MatchRepoImpl;
import repo.impl.PlayerRepoImpl;
import repo.impl.ScoreRepoImpl;
import repo.impl.TeamRepoImpl;
import services.MatchService;
import services.PlayerService;
import services.ScoreService;
import services.TeamService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayerRepo playerRepo = new PlayerRepoImpl();
        PlayerService playerService = new PlayerService(new AtomicLong(1), playerRepo);
        for(Integer i=1; i<=10; i++){
            playerService.registerPlayer("p-"+i.toString());
        }
        TeamRepo teamRepo = new TeamRepoImpl();
        TeamService teamService = new TeamService(new AtomicLong(1), teamRepo, playerService);
        System.out.println(playerRepo.getPlayerById(new Long(1)).get());
        teamService.registerTeam(List.of(new Long(1), new Long(2),new Long(3), new Long(4),new Long(5)),"team rainbow");
        teamService.registerTeam(List.of(new Long(6), new Long(7),new Long(8), new Long(9),new Long(10)),"team master");
        MatchRepo matchRepo = new MatchRepoImpl();
        ScoreRepo scoreRepo = new ScoreRepoImpl();
        ScoreService scoreService = new ScoreService(scoreRepo);
        MatchService matchService = new MatchService(new AtomicLong(1), matchRepo, teamService, scoreService);
        matchService.createMatch(List.of(new Long(1), new Long(2)), " ooklll");
        matchService.updateMatchScore(1, 1, BallType.ONE);
        matchService.updateMatchScore(1, 1, BallType.ONE);
        matchService.updateMatchScore(1, 1, BallType.ONE);
        matchService.updateMatchScore(1, 1, BallType.ONE);
        matchService.updateMatchScore(1, 1, BallType.ONE);
        matchService.updateMatchScore(1, 1, BallType.TWO);
        // W 4 4 Wd W 1 6
        matchService.updateMatchScore(1, 2, BallType.WICKET);
        matchService.updateMatchScore(1, 2, BallType.FOUR);
        matchService.updateMatchScore(1, 2, BallType.FOUR);
        matchService.updateMatchScore(1, 2, BallType.WIDE);
        matchService.updateMatchScore(1, 2, BallType.WICKET);
        matchService.updateMatchScore(1, 2, BallType.ONE);
        matchService.updateMatchScore(1, 2, BallType.SIX);
        System.out.println(scoreService.getPlayerScore(teamRepo.getById(new Long(1)).get()));
    }
}

// started at 2:42 PM
// 15 min model and constant define initially
//8 min interface declaration
// impl started 3:05 PM and end at 3:!4
// started writing services
// player service started 3:15 and ended at 3:21
// started writning  team service 3:21 and done creating tema function at 3:37
// started wrting macth service with create match fucntion at 3:37 and ended at 3:42;
// started writning match update function
// only player score list is implmented not tested in 1 and half hour
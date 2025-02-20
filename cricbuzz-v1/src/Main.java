import constants.BallType;
import repo.MatchRepo;
import repo.PlayerRepo;
import repo.TeamRepo;
import repo.impl.MatchRepoImpl;
import repo.impl.PlayerRepoImpl;
import repo.impl.TeamRepoImpl;
import services.MatchService;
import services.PlayerService;
import services.ScoreService;
import services.TeamService;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayerRepo playerRepo = new PlayerRepoImpl();
        PlayerService playerService = new PlayerService(new AtomicInteger(1), playerRepo);
        for(Integer i = 1;i<11; i++){
            playerService.registerPlayer("player"+i.toString());
        }
        TeamRepo teamRepo = new TeamRepoImpl();
        TeamService teamService = new TeamService(new AtomicInteger(1), teamRepo, playerService);
        teamService.registerTeam("Alpha team", List.of(1,2,3,4,5));
        teamService.registerTeam("Beta team", List.of(6,7,8,9,10));
        MatchRepo matchRepo = new MatchRepoImpl();
        MatchService matchService = new MatchService(new AtomicInteger(1), matchRepo, teamService);
        matchService.createMatch("IPL", List.of(1,2));
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.TWO);
        // W 4 4 Wd W 1 6
        teamService.updateMatchScore(1, 2, BallType.WICKET);
        teamService.updateMatchScore(1, 2, BallType.FOUR);
        teamService.updateMatchScore(1, 2, BallType.FOUR);
        teamService.updateMatchScore(1, 2, BallType.WIDE);
        teamService.updateMatchScore(1, 2, BallType.WICKET);
        teamService.updateMatchScore(1, 2, BallType.ONE);
        teamService.updateMatchScore(1, 2, BallType.SIX);;
        ScoreService scoreService = new ScoreService();
        System.out.println(scoreService.getScoreForOver(teamRepo.getTeamById(1).get()));

    }
}

// total time 1 hour 45 min - second time
// total time 2 hour 15 min - first time
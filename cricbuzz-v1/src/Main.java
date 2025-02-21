import constants.BallType;
import models.Team;
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

public class Main {
    public static void main(String[] args) {
        PlayerRepo playerRepo = new PlayerRepoImpl();
        PlayerService playerService = new PlayerService(new AtomicInteger(1), playerRepo);

        for (int i = 1; i <= 10; i++) {
            playerService.registerPlayer("P" + i);
        }

        TeamRepo teamRepo = new TeamRepoImpl();
        TeamService teamService = new TeamService(new AtomicInteger(1), teamRepo, playerService);
        Team team = teamService.registerTeam("Alpha team", List.of(1, 2, 3, 4, 5));
        teamService.registerTeam("Beta team", List.of(6, 7, 8, 9, 10));

        MatchRepo matchRepo = new MatchRepoImpl();
        MatchService matchService = new MatchService(new AtomicInteger(1), matchRepo, teamService);
        matchService.createMatch("IPL", List.of(1, 2));

        ScoreService scoreService = new ScoreService();

        // Over 1
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.ONE);
        teamService.updateMatchScore(1, 1, BallType.TWO);

        System.out.println("Scorecard after Over 1:");
        System.out.println(scoreService.getScoreForOver(team));

        // Over 2
        teamService.updateMatchScore(1, 2, BallType.WICKET); // P1 Out, P3 comes in
        teamService.updateMatchScore(1, 2, BallType.FOUR);
        teamService.updateMatchScore(1, 2, BallType.FOUR);
        teamService.updateMatchScore(1, 2, BallType.WIDE);
        teamService.updateMatchScore(1, 2, BallType.WICKET); // P2 Out, P4 comes in
        teamService.updateMatchScore(1, 2, BallType.ONE);
        teamService.updateMatchScore(1, 2, BallType.SIX);

        System.out.println("Scorecard after Over 2:");
        // here we could calculate after every ball
        System.out.println(scoreService.getScoreForOver(team));

    }
}

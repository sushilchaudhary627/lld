import constants.BallType;
import models.Team;
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

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        PlayerRepo playerRepo = new PlayerRepoImpl();
        TeamRepo teamRepo = new TeamRepoImpl();
        MatchRepo matchRepo = new MatchRepoImpl();
        ScoreRepo scoreRepo = new ScoreRepoImpl();

        // Initialize services
        PlayerService playerService = new PlayerService(new AtomicLong(1), playerRepo);
        TeamService teamService = new TeamService(new AtomicLong(1), teamRepo, playerService);
        ScoreService scoreService = new ScoreService(scoreRepo);
        MatchService matchService = new MatchService(new AtomicLong(1), matchRepo, teamService, scoreService);

        // Register Players
        for (int i = 1; i <= 10; i++) {
            playerService.registerPlayer("P" + i);
        }

        // Register Teams
        teamService.registerTeam(List.of(1L, 2L, 3L, 4L, 5L), "Team 1");
        teamService.registerTeam(List.of(6L, 7L, 8L, 9L, 10L), "Team 2");

        // Create Match
        matchService.createMatch(List.of(1L, 2L), "Friendly Match");

        // Get teams
        Team team1 = teamRepo.getById(1L).orElseThrow();
        Team team2 = teamRepo.getById(2L).orElseThrow();

        // Simulating innings for Team 1
        System.out.println("\n--- Team 1 Batting ---");
        logStriker(team1);
        matchService.updateMatchScore(1, 1, BallType.ONE);

        logStriker(team1);
        matchService.updateMatchScore(1, 1, BallType.ONE);

        logStriker(team1);
        matchService.updateMatchScore(1, 1, BallType.ONE);

        logStriker(team1);
        matchService.updateMatchScore(1, 1, BallType.ONE);

        logStriker(team1);
        matchService.updateMatchScore(1, 1, BallType.ONE);

        logStriker(team1);
        matchService.updateMatchScore(1, 1, BallType.TWO);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.WICKET);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.FOUR);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.FOUR);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.WIDE);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.WICKET);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.ONE);

        logStriker(team1);
        matchService.updateMatchScore(1, 2, BallType.SIX);

        System.out.println("\nFinal Scorecard for Team 1:");
        System.out.println(scoreService.getPlayerScore(team1));

        // Simulating innings for Team 2
        System.out.println("\n--- Team 2 Batting ---");
        logStriker(team2);
        matchService.updateMatchScore(2, 1, BallType.FOUR);

        logStriker(team2);
        matchService.updateMatchScore(2, 1, BallType.SIX);

        logStriker(team2);
        matchService.updateMatchScore(2, 1, BallType.WICKET);

        logStriker(team2);
        matchService.updateMatchScore(2, 1, BallType.WICKET);

        logStriker(team2);
        matchService.updateMatchScore(2, 1, BallType.ONE);

        logStriker(team2);
        matchService.updateMatchScore(2, 1, BallType.ONE);
        System.out.println(scoreService.getPlayerScore(team2));

        logStriker(team2);
        matchService.updateMatchScore(2, 2, BallType.SIX);

        logStriker(team2);
        matchService.updateMatchScore(2, 2, BallType.ONE);

        logStriker(team2);
        matchService.updateMatchScore(2, 2, BallType.WICKET);

        logStriker(team2);
        matchService.updateMatchScore(2, 2, BallType.WICKET);

        System.out.println("\nFinal Scorecard for Team 2:");
        System.out.println(scoreService.getPlayerScore(team2));

        // Determine match result
        System.out.println("\nMatch Result: ");
    }

    private static void logStriker(Team team) {
        System.out.println("Striker: " + team.getStriker().getName());
    }
}


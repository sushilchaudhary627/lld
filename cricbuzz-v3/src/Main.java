import constants.BallType;
import repo.MatchRepo;
import repo.PlayerRepo;
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

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayerRepo playerRepo = new PlayerRepoImpl();
        PlayerService playerService = new PlayerService(new AtomicLong(1), playerRepo);
        for (Integer i = 1; i <= 10; i++) {
            playerService.registerPlayer(String.format("P-%s".formatted(i.toString())));
        }
        TeamRepo teamRepo = new TeamRepoImpl();
        TeamService teamService = new TeamService(new AtomicLong(1), teamRepo, playerService);
        teamService.registerTeam(List.of(1L, 2L, 3L, 4L, 5L), "tomtom");
        teamService.registerTeam(List.of(6L, 7L, 8L, 9L, 10L), "hyhyhy");
        MatchRepo matchRepo = new MatchRepoImpl();
        ScoreService scoreService = new ScoreService(new ScoreRepoImpl());
        MatchService matchService = new MatchService(new AtomicLong(1), matchRepo, teamService, scoreService);
        //Over 1: 1 1 1 1 1 2

        matchService.createMatch(List.of(1L, 2L), "mohlla match", 2);
        matchService.processMatchUpdate(1L, 1L, BallType.ONE, 1);
        matchService.processMatchUpdate(1L, 1L, BallType.ONE, 1);
        matchService.processMatchUpdate(1L, 1L, BallType.ONE, 1);
        matchService.processMatchUpdate(1L, 1L, BallType.ONE, 1);
        matchService.processMatchUpdate(1L, 1L, BallType.ONE, 1);
        matchService.processMatchUpdate(1L, 1L, BallType.TWO, 1);
        // Over 2: W 4 4 Wd W 1 6
        System.out.println(scoreService.getScorePlayerWise(teamRepo.findTeamById(1L).get()));
        List<BallType> ballTypes = List.of(BallType.WICKET, BallType.FOUR, BallType.FOUR, BallType.WIDE, BallType.WICKET, BallType.ONE, BallType.SIX);
        for (BallType ballType : ballTypes) {
            matchService.processMatchUpdate(1L, 1L, ballType, 2);
        }
        System.out.println(scoreService.getScoreOfTeam(teamRepo.findTeamById(1L).get()));
        System.out.println(scoreService.getScorePlayerWise(teamRepo.findTeamById(1L).get()));
    }
}

// problem statement reading - 12:28 - 12:33
// started writing models - 12:33 -12:45
// started writing repo - 12:45 -12:51 only wrote interface def
// started impl interface impl - 12:51 and completed 1:00 AM
// started writing service layer 1:01AM - player service and completed 1:07 AM
// started writing Team service 1:07AM - and completed in 1:16AM
// started writing matchService for creating match - 1:18AM and ended at 1:27AM
// started writing ball update function 1:28AM and able to implement striker chnage 1:48
// started implementing score and completed 2:44AM I also not taken extra ball considered to count current over ended
//

//lot of backspace i can see
// here mostly i spent lot of time in score one reason not correctly know stream API
// also not clear with extra runs not added in ball

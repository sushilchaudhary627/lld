import constants.BallType;
import repo.PlayerRepo;
import repo.TeamRepo;
import repo.impl.MatchRepoImpl;
import repo.impl.PlayerRepoImpl;
import repo.impl.PlayerRunRepoImpl;
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
        for(Integer i=1; i<=10; i++){
            playerService.registerPlayer(String.format("P-%s", i.toString()));
        }
        TeamRepo teamRepo = new TeamRepoImpl();
        TeamService teamService = new TeamService(new AtomicLong(1), teamRepo, playerService);
        teamService.registerTeam("Breaker", List.of(1L,2L,3L,4L,5L));
        teamService.registerTeam("Dreamer", List.of(6L,7L,8L,9L,10L));

        ScoreService scoreService = new ScoreService(new PlayerRunRepoImpl());
        MatchService matchService = new MatchService(new AtomicLong(1), new MatchRepoImpl(), teamService, scoreService);
        matchService.createMatch("Mohalla match", 2, List.of(1L,2L));
        // W 4 4 Wd W 1 6
        List<BallType>ballTypes = List.of(BallType.ONE, BallType.ONE,BallType.ONE,BallType.ONE,BallType.ONE,BallType.TWO,BallType.WICKET,
            BallType.FOUR,BallType.FOUR,BallType.WIDE,BallType.WICKET,BallType.ONE,BallType.SIX
        );
        ballTypes.forEach(ballType -> {
            matchService.processMatchUpdate(1L,1L,ballType);
        });

        System.out.println(scoreService.getScoreCardForTeam(teamRepo.findTeamById(1L).get()));
        List<BallType>ballTypes1 = List.of(BallType.FOUR,BallType.SIX,BallType.WICKET,BallType.WICKET,BallType.ONE, BallType.ONE,BallType.SIX,BallType.ONE,BallType.WICKET,BallType.WICKET);
        // 4 6 W W 1 1
        //6 1 W W
        ballTypes1.forEach(ballType -> {
        matchService.processMatchUpdate(1L,2L,ballType);
        });
        System.out.println(scoreService.getScoreCardForTeam(teamRepo.findTeamById(2L).get()));

    }
}

// started writing code at 3:12PM
// models writing at 3:13 and completed 3:27PM
// started writing repo  3:27 -and completed impl 3:41
// took 2 hour 15 min 

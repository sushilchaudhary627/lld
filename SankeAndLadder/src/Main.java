import models.Dice;
import models.Game;
import models.Player;
import repo.BoardRepo;
import repo.PlayerRepo;
import repo.impl.BoardRepoImpl;
import repo.impl.GameRepoImpl;
import repo.impl.PlayerRepoImpl;
import services.BoardService;
import services.GameService;
import services.PlayerService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PlayerRepo playerRepo = new PlayerRepoImpl();
        PlayerService playerService = new PlayerService(new AtomicInteger(1), playerRepo);
        playerService.registerPlayer("Abhy");
        playerService.registerPlayer("karan");
        playerService.registerPlayer("Abhi");
        BoardRepo boardRepo = new BoardRepoImpl();
        BoardService boardService = new BoardService(new AtomicInteger(1), boardRepo);
        boardService.createBoard(Map.of(10, 1,28,6), Map.of(2, 10, 9, 100), 100);
        Dice dice = new Dice(6);
        GameService gameService = new GameService(new AtomicInteger(1), new GameRepoImpl(), boardService, playerService);
        Game game = gameService.createGame(1, List.of(1,2));
        while(game.getWinner() == null){
            Integer player = game.getPlayerOrders().getFirst().getPlayerId();
            int value = dice.roll();
            System.out.println(game.getPlayerOrders().getFirst().getName());
            System.out.println(value);
            gameService.takeMove(player, 1,dice.roll());
        }
        System.out.println(game.getPlayerPositions());
    }
}

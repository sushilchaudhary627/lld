import models.Board;
import models.Dice;
import models.Game;
import models.Player;
import repository.BoardRepository;
import repository.GameRepository;
import repository.PlayerRepository;
import repository.impl.BoardRepositoryImpl;
import repository.impl.GameRepositoryImpl;
import repository.impl.PlayerRepositoryImpl;
import services.BoardService;
import services.GameService;
import services.PlayerService;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BoardRepository boardRepository = new BoardRepositoryImpl();
        GameRepository gameRepository = new GameRepositoryImpl();
        PlayerRepository playerRepository = new PlayerRepositoryImpl();
        PlayerService playerService = new PlayerService(playerRepository, new AtomicInteger(1));
        BoardService boardService = new BoardService(boardRepository, new AtomicInteger(1));
        GameService gameService = new GameService(gameRepository, new Dice(), playerService, boardService);
        Board board = boardService.createBoard(Map.of(10, 6, 55, 20), Map.of(2, 19, 30, 99));
        System.out.println(board);
        Game game = gameService.startGame(board.getBoardId());
        System.out.println(game);
        Player p1 = playerService.createPlayer("A");
        Player p2 = playerService.createPlayer("B");
        gameService.registerPlayerToGame(game.getGameId(), p1.getPlayerId());
        gameService.registerPlayerToGame(game.getGameId(), p2.getPlayerId());
        System.out.println(game);
        gameService.takeTurn(p1.getPlayerId(), game.getGameId());
        while(game.getWinner() == null) {
            try {
                gameService.takeTurn(p1.getPlayerId(), game.getGameId());
                System.out.println(game);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

            try {
                gameService.takeTurn(p2.getPlayerId(), game.getGameId());
                System.out.println(game);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }

    }
}
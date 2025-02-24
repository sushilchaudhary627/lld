package services;

import models.*;
import repo.GameRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class GameService {
    private final AtomicInteger gameIdGenerator;
    private final GameRepo gameRepo;
    private final BoardService boardService;
    private final PlayerService playerService;

    public GameService(AtomicInteger gameIdGenerator, GameRepo gameRepo, BoardService boardService, PlayerService playerService) {
        this.gameIdGenerator = gameIdGenerator;
        this.gameRepo = gameRepo;
        this.boardService = boardService;
        this.playerService = playerService;
    }

    public Game createGame(Integer boardId, List<Integer> playerIds){
        Board board = boardService.findBoardById(boardId);
        List<Player>players = playerService.findPlayersById(playerIds);
        List<PlayerPosition>playerPositions = players.stream()
            .map(p -> new PlayerPosition(p, board.getTile(1)))
            .toList();
        if(players.size() != playerIds.size()){
            throw new RuntimeException("Few players are not found.");
        }
        Game game = new Game(gameIdGenerator.getAndIncrement(), players, board);
        for(PlayerPosition playerPosition: playerPositions){
            game.addNewPlayerPos(playerPosition);
        }
        gameRepo.save(game);
        return game;
    }

    public Game takeMove(Integer playerId, Integer gameId, Integer diceValue){
        Game game = gameRepo.findGameById(gameId).orElseThrow();
        if(!Objects.isNull(game.getWinner())){
            throw new RuntimeException("Game is ended.");
        }
        Player player = game.getPlayers().stream()
            .filter(p -> p.getPlayerId().equals(playerId))
            .findFirst()
            .orElseThrow();
        if(!isPlayerMoveAllowed(game, player)){
            throw new RuntimeException("it is not you turn");
        }
        PlayerPosition playerPosition = game.getPlayerPosition(player);
        Tile currentTile = playerPosition.getTile();
        int nextPos = currentTile.getPosition() + diceValue;
        if(nextPos > game.getBoard().getTiles().size()){
            System.out.println("Move is not allowed.");
        }
        Tile nextTile = game.getBoard().getTile(nextPos);
        playerPosition.setTile(nextTile);
        if(isGameOver(game)){
            System.out.printf("Game is over and winner is %s", player.getName());
            game.setWinner(player);
        }
        updatePlayerTurn(game);
        gameRepo.save(game);
        return game;
    }

    public boolean isGameOver(Game game){
        return game.getPlayerPositions().stream()
            .anyMatch(playerPosition -> game.getBoard().isWinningTile(playerPosition.getTile()));
    }

    private boolean isPlayerMoveAllowed(Game game, Player player){
        Player allowedPlayer = game.getPlayerOrders().getFirst();
        if(allowedPlayer.getPlayerId().equals(player.getPlayerId())){
            return true;
        }
        return false;
    }

    public void updatePlayerTurn(Game game){
        Player player = game.getPlayerOrders().pollFirst();
        game.getPlayerOrders().addLast(player);
    }
}

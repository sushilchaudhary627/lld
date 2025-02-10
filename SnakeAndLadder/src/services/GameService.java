package services;

import models.*;
import repository.GameRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class GameService {
    private final GameRepository gameRepository;
    private final Dice dice;
    private final PlayerService playerService;
    private final BoardService boardService;
    private final AtomicInteger gameIdGenerator = new AtomicInteger(1);

    public GameService(GameRepository gameRepository, Dice dice, PlayerService playerService, BoardService boardService) {
        this.gameRepository = gameRepository;
        this.dice = dice;
        this.playerService = playerService;
        this.boardService = boardService;
    }

    /**
     * Starts a new game using the specified board ID.
     *
     * @param boardId the ID of the board to use for the game.
     * @return the created Game instance.
     */
    public Game startGame(Integer boardId) {
        Board board = boardService.getBoardById(boardId);
        Game game = new Game(gameIdGenerator.getAndIncrement(), board);
        gameRepository.save(game);
        return game;
    }

    /**
     * Registers a player to an existing game.
     *
     * @param gameId   the ID of the game.
     * @param playerId the ID of the player to register.
     * @return the updated Game instance.
     */
    public Game registerPlayerToGame(Integer gameId, Integer playerId) {
        Game game = gameRepository.findGameById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + gameId));
        Player player = playerService.getPlayer(playerId);
        PlayerPosition playerPosition = createInitialPlayerPosition(game, player);
        game.addPlayerPosition(playerPosition);
        gameRepository.update(game);
        return game;
    }

    /**
     * Processes a turn for the specified player in the given game.
     *
     * @param playerId the ID of the player taking the turn.
     * @param gameId   the ID of the game.
     * @return the updated Game instance.
     */
    public Game takeTurn(Integer playerId, Integer gameId) {
        Player player = playerService.getPlayer(playerId);
        Game game = gameRepository.findGameById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game not found with ID: " + gameId));

        if (game.isGameComplete()) {
            throw new IllegalArgumentException("Game is over.");
        }

        PlayerPosition playerPosition = game.getPlayerPosition(player);
        int rollValue = dice.roll();
        Cell currentCell = playerPosition.getCell();
        Cell nextCell = determineNextCell(game.getBoard(), currentCell, rollValue);

        playerPosition.setCell(nextCell);
        if (boardService.isWinningPosition(nextCell)) {
            game.setWinner(player);
        }

        gameRepository.update(game);
        return game;
    }

    /**
     * Creates the initial player position on the board.
     *
     * @param game   the game instance.
     * @param player the player to position.
     * @return a new PlayerPosition starting at the board's start cell.
     */
    private PlayerPosition createInitialPlayerPosition(Game game, Player player) {
        PlayerPosition position = new PlayerPosition();
        position.setPlayer(player);
        position.setCell(game.getBoard().getStartCell());
        return position;
    }

    /**
     * Determines the next cell based on the current cell and the dice roll.
     *
     * @param board      the board for the game.
     * @param currentCell the current cell of the player.
     * @param rollValue  the value obtained from the dice roll.
     * @return the next Cell.
     */
    private Cell determineNextCell(Board board, Cell currentCell, int rollValue) {
        int nextPosition = currentCell.getNextPosition(rollValue);  // Ensure nextPosition() is defined with a lowercase 'n'
        return board.findCellByPosition(nextPosition)
                .orElseThrow(() -> new RuntimeException(
                        "Move not allowed from position " + currentCell.getPosition() + " with roll " + rollValue));
    }
}

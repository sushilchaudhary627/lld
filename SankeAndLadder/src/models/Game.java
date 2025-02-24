package models;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Game {
    private final Integer gameId;
    private final List<Player> players;
    private final List<PlayerPosition>playerPositions;
    private final Board board;
    private Player winner;
    private final Deque<Player>playerOrders;
    public Game(Integer gameId, List<Player>players, Board board) {
        this.gameId = gameId;
        this.players = players;
        this.board = board;
        playerOrders = new ArrayDeque<>(players);
        this.playerPositions = new ArrayList<>();
    }

    public void addNewPlayerPos(PlayerPosition playerPosition){
        playerPositions.add(playerPosition);
    }

    public PlayerPosition getPlayerPosition(Player player){
        return playerPositions.stream()
            .filter( playerPosition -> playerPosition.getPlayer().getPlayerId().equals(player.getPlayerId()))
            .findFirst()
            .orElseThrow();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public Integer getGameId() {
        return gameId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<PlayerPosition> getPlayerPositions() {
        return playerPositions;
    }

    public Board getBoard() {
        return board;
    }

    public Deque<Player> getPlayerOrders() {
        return playerOrders;
    }
}

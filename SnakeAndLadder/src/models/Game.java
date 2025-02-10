package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game {
    public Game(List<PlayerPosition> playerPositions, Board board) {
        this.playerPositions = playerPositions;
        this.board = board;
    }

    public Game(Integer gameId, Board board) {
        this.playerPositions = new ArrayList<>();
        this.board = board;
        this.gameId = gameId;
    }

    List<PlayerPosition> playerPositions;
    Board board;
    Player winner;
    Integer gameId;

    public Game() {

    }

    public List<PlayerPosition> getPlayerPositions() {
        return playerPositions;
    }

    public Board getBoard() {
        return board;
    }

    public PlayerPosition getPlayerPosition(Player player){
        return playerPositions.stream().filter((PlayerPosition playerPosition) -> playerPosition.getPlayer().equals(player)).findFirst().orElseThrow();
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addPlayerPosition(PlayerPosition playerPosition) {
        playerPositions.add(playerPosition);
    }

    public Integer getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return "Game{" +
                "playerPositions=" + playerPositions +
                ", board=" + board.getBoardId().toString() +
                ", winner=" + winner +
                ", gameId=" + gameId +
                '}';
    }

    public Boolean isGameComplete(){
        return !Objects.isNull(winner);
    }
}

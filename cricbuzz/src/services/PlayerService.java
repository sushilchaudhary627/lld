package services;

import models.Player;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerService {
    private final AtomicInteger playerIdGenerator;
    private final PlayerRepository playerRepository;
    public PlayerService(AtomicInteger playerIdGenerator, PlayerRepository playerRepository){
        this.playerIdGenerator = playerIdGenerator;
        this.playerRepository = playerRepository;
    }
    public Player createPlayer(String playerName){
        validatePlayerCreateRequest(playerName);
        Integer playerId = playerIdGenerator.getAndIncrement();
        Player player = new Player(playerId, playerName);
        playerRepository.save(player);
        return player;
    }

    private void validatePlayerCreateRequest(String playerName){
        Objects.requireNonNull(playerName, "Player name can't be null");
        if(playerName.isEmpty()){
            throw new RuntimeException("Player name can't be empty");
        }
    }

    public Player getPlayerById(Integer playerId){
        return null;
    }

    public List<Player> getPlayers(List<Integer> playerIds){
        return playerRepository.getPlayersBYId(playerIds);
    }
}

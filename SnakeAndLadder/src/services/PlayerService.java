package services;
import models.*;
import repository.PlayerRepository;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final AtomicInteger playerIdGenerator ;

    public PlayerService(PlayerRepository playerRepository, AtomicInteger playerIdGenerator) {
        this.playerRepository = playerRepository;
        this.playerIdGenerator = playerIdGenerator;
    }

    public Player getPlayer(Integer playerId) {
        return playerRepository.getPlayerById(playerId);
    }
    public Player createPlayer(String playerName){
        Player player = new Player();
        player.setPlayerId(playerIdGenerator.getAndIncrement());
        player.setName(playerName);
        playerRepository.save(player);
        return player;
    }
}

package services;

import models.Player;
import repo.PlayerRepo;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerService {
    private final AtomicInteger playerIdGenerator;
    private final PlayerRepo playerRepo;

    public PlayerService(AtomicInteger playerIdGenerator, PlayerRepo playerRepo) {
        this.playerIdGenerator = playerIdGenerator;
        this.playerRepo = playerRepo;
    }

    public Player registerPlayer(String name){
        validateRegisterPlayerRequest(name);
        Player player = new Player(playerIdGenerator.getAndIncrement(), name);
        playerRepo.save(player);
        return player;
    }

    private void validateRegisterPlayerRequest(String name) {
        if(Objects.isNull(name) || name.isEmpty()){
            throw new RuntimeException("name is required and non-empty field.");
        }
        if(playerRepo.findPlayerByName(name).isPresent()){
            throw new RuntimeException("Player with same name already exists");
        }
    }

    public List<Player> findPlayersById(List<Integer> playerIds) {
        return playerRepo.findPlayersById(playerIds);
    }
}

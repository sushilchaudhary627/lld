package services;

import models.Player;
import repo.PlayerRepo;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class PlayerService {
    private final AtomicLong playerIdGen;
    private final PlayerRepo playerRepo;

    public PlayerService(AtomicLong playerIdGen, PlayerRepo playerRepo) {
        this.playerIdGen = playerIdGen;
        this.playerRepo = playerRepo;
    }

    public Player registerPlayer(String name){
        validatePlayerRegisterReq(name);
        Player player = new Player(playerIdGen.getAndIncrement());
        player.setName(name);
        playerRepo.save(player);
        System.out.println("Player:" + player.getName() + " registered successfully.");
        return player;
    }

    private void validatePlayerRegisterReq(String name) {
        Objects.requireNonNull(name, "Name should not be null");
        if(name.isEmpty()){
            throw new RuntimeException("Name should not be empty.");
        }
    }

    public List<Player> findPlayers(List<Long> playerIds) {
        return playerRepo.findPlayers(playerIds);
    }
}

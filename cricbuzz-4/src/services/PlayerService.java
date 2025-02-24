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
        validateRegisterPlayerReq(name);
        Player player = new Player(playerIdGen.getAndIncrement());
        player.setName(name);
        playerRepo.save(player);
        System.out.printf("Player: %s is registered successfully.\n", player.getName());
        return player;
    }

    private void validateRegisterPlayerReq(String name) {
        Objects.requireNonNull(name, "Name must be present");
        if(name.isEmpty()){
            throw new RuntimeException("Player name should not be empty");
        }
    }

    public List<Player> getPlayersById(List<Long> playerIdS) {
        return playerRepo.findPlayersById(playerIdS);
    }
}

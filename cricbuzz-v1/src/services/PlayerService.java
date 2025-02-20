package services;

import models.Player;
import repo.PlayerRepo;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerService {
    private final AtomicInteger playerIdGen;
    private final PlayerRepo playerRepo;

    public PlayerService(AtomicInteger playerIdGen, PlayerRepo playerRepo) {
        this.playerIdGen = playerIdGen;
        this.playerRepo = playerRepo;
    }

    public Player registerPlayer(String name){
        validatePlayerCreateReq(name);
        Player player = new Player(playerIdGen.getAndIncrement(), name);
        playerRepo.save(player);
        return player;
    }

    private void validatePlayerCreateReq(String name){
        Objects.requireNonNull(name, "Name should not be null.");
        if(name.isEmpty()){
            throw new RuntimeException("Name should not be empty");
        }
    }

    public Player getPlayerById(Integer id){
        return playerRepo.getPlayerById(id).orElseThrow();
    }


    public List<Player> getPlayers(List<Integer> playerIds) {
        return playerRepo.getPlayers(playerIds);
    }
}

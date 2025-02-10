package repository.impl;

import models.Player;
import repository.PlayerRepository;

import java.util.HashMap;
import java.util.Map;

public class PlayerRepositoryImpl implements PlayerRepository {
    Map<Integer, Player> playerMap = new HashMap<>();
    @Override
    public void save(Player player) {
        playerMap.put(player.getPlayerId(), player);
    }

    @Override
    public Player getPlayerById(Integer playerId) {
        return playerMap.get(playerId);
    }
}

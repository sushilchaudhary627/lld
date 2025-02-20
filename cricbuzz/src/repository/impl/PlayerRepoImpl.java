package repository.impl;

import models.Player;
import repository.PlayerRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepoImpl implements PlayerRepository {
    Map<Integer, Player> playerMap = new HashMap<>();
    @Override
    public void save(Player player) {
        playerMap.put(player.getPlayerId(), player);
    }

    @Override
    public List<Player> getPlayersBYId(List<Integer> playerIds) {
        List<Player>players = new ArrayList<>();
        for(Integer playerId:playerIds){
            if(playerMap.containsKey(playerId)){
                players.add(playerMap.get(playerId));
            }
        }
        return players;
    }
}

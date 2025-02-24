package repo.impl;

import models.Player;
import repo.PlayerRepo;

import java.util.*;

public class PlayerRepoImpl implements PlayerRepo {
    Map<Long, Player>playerMap = new HashMap<>();
    @Override
    public void save(Player p) {
        playerMap.put(p.getId(), p);
    }

    @Override
    public Optional<Player> getPlayerById(Long id) {
        return Optional.ofNullable(playerMap.get(id));
    }

    @Override
    public List<Player> getPlayers(List<Long> playerIds) {
        List<Player>players = new ArrayList<>();
        for(Long id:playerIds){
            if(playerMap.containsKey(id)){
                players.add(playerMap.get(id));
            }
        }
        return players;
    }
}

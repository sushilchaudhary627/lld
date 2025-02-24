package repo.impl;

import models.Player;
import repo.PlayerRepo;

import java.util.*;
import java.util.Optional;

public class PlayerRepoImpl  implements PlayerRepo {
    Map<Integer, Player>players = new HashMap<>();
    @Override
    public void save(Player player) {
        players.put(player.getId(), player);
        System.out.println("player is saved:"+ player.getName());
    }

    @Override
    public List<Player> getPlayers(List<Integer> playerIds) {
        List<Player>playerList = new ArrayList<>();
        for(Integer playerId:playerIds){
            if(players.containsKey(playerId)){
                playerList.add(players.get(playerId));
            }
        }
        return playerList;
    }

    @Override
    public Optional<Player> getPlayerById(Integer id) {
        return Optional.ofNullable(players.get(id));
    }
}

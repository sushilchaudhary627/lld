package repo.impl;

import models.Player;
import repo.PlayerRepo;

import java.util.*;

public class PlayerRepoImpl implements PlayerRepo {
    private final Map<Integer, Player> playerByIdMap = new HashMap<>();
    private final Map<String, Player> playerByNameMap = new HashMap<>();
    @Override
    public void save(Player player) {
        playerByIdMap.put(player.getPlayerId(), player);
        playerByNameMap.put(player.getName(), player);
    }

    @Override
    public void update(Player player) {
    }

    @Override
    public Optional<Player> findPlayerById(Integer id) {
        return Optional.ofNullable(playerByIdMap.get(id));
    }

    @Override
    public Optional<Player> findPlayerByName(String name) {
        return Optional.ofNullable(playerByNameMap.get(name));
    }

    @Override
    public List<Player> findPlayersById(List<Integer> playerIds) {
        return playerIds.stream()
            .map(playerId -> playerByIdMap.get(playerId))
            .filter( p -> Objects.nonNull(p))
            .toList();
    }
}

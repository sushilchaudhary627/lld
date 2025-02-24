package repo.impl;

import models.Player;
import repo.PlayerRepo;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerRepoImpl implements PlayerRepo {
    Map<Long, Player> playerMap = new HashMap<>();
    @Override
    public void save(Player player) {
         playerMap.put(player.getId(), player);
    }

    @Override
    public Optional<Player> findPlayerById(Long id) {
        return Optional.ofNullable(playerMap.get(id));
    }

    @Override
    public List<Player> findPlayers(List<Long> playerIds) {
        return playerIds.stream().map(id -> playerMap.get(id)).filter(p ->Objects.nonNull(p)).collect(Collectors.toList());
    }
}

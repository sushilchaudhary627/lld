package repo.impl;

import models.Player;
import models.PlayerRunDetail;
import repo.ScoreRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreRepoImpl implements ScoreRepo {
    Map<Player, List<PlayerRunDetail>> playerListMap = new HashMap<>();
    @Override
    public void save(PlayerRunDetail playerRunDetail) {
        playerListMap.putIfAbsent(playerRunDetail.getPlayer(), new ArrayList<>());
        playerListMap.get(playerRunDetail.getPlayer()).add(playerRunDetail);
    }

    @Override
    public void update(PlayerRunDetail playerRunDetail) {

    }

    @Override
    public List<PlayerRunDetail> findRuns(Player player) {
        return playerListMap.getOrDefault(player, List.of());
    }
}

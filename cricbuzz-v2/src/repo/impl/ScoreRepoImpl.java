package repo.impl;

import models.Player;
import models.PlayerRunDetail;
import repo.ScoreRepo;

import java.util.*;

public class ScoreRepoImpl  implements ScoreRepo {
    Map<Player, List<PlayerRunDetail>>playerScoreMap = new HashMap<>();
    @Override
    public void save(PlayerRunDetail playerRunDetail) {
        playerScoreMap.putIfAbsent(playerRunDetail.getPlayer(), new ArrayList<>());
        playerScoreMap.get(playerRunDetail.getPlayer()).add(playerRunDetail);
    }

    @Override
    public List<PlayerRunDetail> getPlayerRuns(Player player) {
        return playerScoreMap.getOrDefault(player, List.of());
    }

    @Override
    public void update(PlayerRunDetail playerRunDetail) {

    }
}

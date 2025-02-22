package repo.impl;

import models.Player;
import models.PlayerRunDetail;
import repo.PlayerRunRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRunRepoImpl implements PlayerRunRepo {
    Map<Player, List<PlayerRunDetail>> playerRunDetails = new HashMap<>();
    @Override
    public void save(PlayerRunDetail playerRunDetail) {
        playerRunDetails.putIfAbsent(playerRunDetail.getPlayer(), new ArrayList<>());
        playerRunDetails.get(playerRunDetail.getPlayer()).add(playerRunDetail);
    }

    @Override
    public void update(PlayerRunDetail playerRunDetail) {

    }

    @Override
    public List<PlayerRunDetail> findRunsFor(Player player) {
        return playerRunDetails.getOrDefault(player, List.of());
    }
}

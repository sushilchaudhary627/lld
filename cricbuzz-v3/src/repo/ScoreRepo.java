package repo;

import models.Player;
import models.PlayerRunDetail;

import java.util.List;

public interface ScoreRepo {
    public void save(PlayerRunDetail playerRunDetail);
    public void update(PlayerRunDetail playerRunDetail);
    public List<PlayerRunDetail>findRuns(Player player);
}

package repo;
import java.util.*;
import models.*;
public interface ScoreRepo {
    public void save(PlayerRunDetail playerRunDetail);
    public List<PlayerRunDetail> getPlayerRuns(Player player);
    public void update(PlayerRunDetail playerRunDetail);
}

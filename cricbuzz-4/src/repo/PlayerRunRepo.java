package repo;
import models.*;
import java.util.*;
public interface PlayerRunRepo {
    public void save(PlayerRunDetail playerRunDetail);
    public void update(PlayerRunDetail playerRunDetail);
    public List<PlayerRunDetail>findRunsFor(Player player);
}

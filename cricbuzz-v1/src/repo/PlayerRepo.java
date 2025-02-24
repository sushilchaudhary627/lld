package repo;
import java.util.*;
import models.*;
public interface PlayerRepo {
    public void save(Player player);
    public List<Player>getPlayers(List<Integer>playerIds);
    public Optional<Player>getPlayerById(Integer id);
}

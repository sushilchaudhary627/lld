package repo;
import java.util.*;
import models.*;
public interface PlayerRepo {
    public void save(Player player);
    public void update(Player player);
    public Optional<Player>findPlayerById(Integer id);
    public Optional<Player>findPlayerByName(String name);
    public List<Player>findPlayersById(List<Integer>playerIds);

}

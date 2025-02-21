package repo;
import java.util.*;
import models.*;
public interface PlayerRepo {
    public void save(Player p);
    public Optional<Player>getPlayerById(Long id);
    public List<Player>getPlayers(List<Long>playerIds);
}

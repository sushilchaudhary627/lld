package repo;
import java.util.*;
import models.*;
public interface PlayerRepo {
    public void save(Player player);
    public Optional<Player>findPlayerById(Long id);
    public List<Player>findPlayers(List<Long>playerIds);
}

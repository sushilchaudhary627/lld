package repo;
import models.*;
import java.util.*;
public interface PlayerRepo {
    void save(Player player);
    public Optional<Player>findPlayerById(Long id);
    public List<Player> findPlayersById(List<Long>playerIds);
}

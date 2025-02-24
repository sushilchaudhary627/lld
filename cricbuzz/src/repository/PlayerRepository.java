package repository;

import models.Player;

import java.util.List;

public interface PlayerRepository {
    public void save(Player player);
    public List<Player> getPlayersBYId(List<Integer>playerIds);

}

package repository;
import models.*;
public interface PlayerRepository {
    public void save(Player player);
    public Player getPlayerById(Integer playerId);
}

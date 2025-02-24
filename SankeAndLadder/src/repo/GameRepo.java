package repo;
import java.util.*;
import models.*;

public interface GameRepo {
    public void save(Game game);
    public void update(Game game);
    public Optional<Game>findGameById(Integer id);
}

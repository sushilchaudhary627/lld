package repo;
import java.util.*;
import models.*;
public interface MatchRepo {
    public void save(Match match);
    public Optional<Match>getMatchById(Integer id);
    public void update(Match match);
}

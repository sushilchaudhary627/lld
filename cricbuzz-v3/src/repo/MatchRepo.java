package repo;
import java.util.*;
import models.*;
public interface MatchRepo {
    public void save(Match match);
    public Optional<Match> findMatchById(Long id);
}

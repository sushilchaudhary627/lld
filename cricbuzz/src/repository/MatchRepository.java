package repository;
import models.*;

import java.util.Optional;

public interface MatchRepository {
    public void save(Match match);
    public Optional<Match> getMatchById(Integer matchId);
}

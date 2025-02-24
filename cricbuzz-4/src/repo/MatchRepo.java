package repo;

import models.*;

import java.util.Optional;

public interface MatchRepo {
    public void save(Match match);
    public Optional<Match> findMatchById(Long id);
}

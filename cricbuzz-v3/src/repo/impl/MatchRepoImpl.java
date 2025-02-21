package repo.impl;

import models.Match;
import repo.MatchRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MatchRepoImpl implements MatchRepo {
    Map<Long, Match> matchMap = new HashMap<>();
    @Override
    public void save(Match match) {
        matchMap.put(match.getId(), match);
    }

    @Override
    public Optional<Match> findMatchById(Long id) {
        return Optional.ofNullable(matchMap.get(id));
    }
}

package repo.impl;

import models.Match;
import repo.MatchRepo;

import java.util.*;

public class MatchRepoImpl implements MatchRepo {
    Map<Long, Match>matchMap = new HashMap<>();
    @Override
    public void save(Match match) {
        matchMap.put(match.getId(), match);
    }

    @Override
    public Optional<Match> getById(Integer id) {
        return Optional.ofNullable(matchMap.get(id));
    }
}

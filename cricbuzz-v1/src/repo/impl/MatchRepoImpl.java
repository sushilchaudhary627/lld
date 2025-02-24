package repo.impl;

import java.util.*;
import models.*;
import repo.MatchRepo;


public class MatchRepoImpl implements MatchRepo {
    private final Map<Integer, Match>matchMap = new HashMap<>();
    @Override
    public void save(Match match) {
        matchMap.put(match.getMatchId(), match);
    }

    @Override
    public Optional<Match> getMatchById(Integer id) {
        return Optional.ofNullable(matchMap.get(id));
    }

    @Override
    public void update(Match match) {

    }
}

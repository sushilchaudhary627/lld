package repository.impl;

import models.Match;
import repository.MatchRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MatchRepoImpl implements MatchRepository {
    Map<Integer, Match> matchMap = new HashMap<>();
    @Override
    public void save(Match match) {
        matchMap.put(match.getMatchId(), match);
    }

    @Override
    public Optional<Match> getMatchById(Integer matchId) {
        return Optional.ofNullable(matchMap.get(matchId));
    }
}

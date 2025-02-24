package repo.impl;

import models.Game;
import repo.GameRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepoImpl implements GameRepo {
    Map<Integer, Game> gameByIdMap = new HashMap<>();
    @Override
    public void save(Game game) {
        gameByIdMap.put(game.getGameId(), game);
    }

    @Override
    public void update(Game game) {
      save(game);
    }

    @Override
    public Optional<Game> findGameById(Integer id) {
        return Optional.ofNullable(gameByIdMap.get(id));
    }
}

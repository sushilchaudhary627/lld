package repository.impl;

import models.Game;
import repository.GameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepositoryImpl implements GameRepository {
    Map<Integer, Game> gameMap = new HashMap<>();
    @Override
    public void save(Game game) {
        gameMap.put(game.getGameId(), game);
    }

    @Override
    public Optional<Game> findGameById(Integer gameId) {
        return Optional.ofNullable(gameMap.get(gameId));
    }

    @Override
    public void update(Game game) {

    }
}

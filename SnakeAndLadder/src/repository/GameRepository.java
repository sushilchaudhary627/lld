package repository;

import models.Game;

import java.util.Optional;

public interface GameRepository {
    void save(Game game);

    Optional<Game> findGameById(Integer gameId);

    void update(Game game);
}

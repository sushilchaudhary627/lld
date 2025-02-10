package repository;

import models.Board;

import java.util.Optional;

public interface BoardRepository {
    void save(Board board);
    public Optional<Board>getBoardById(Integer boardId);
}

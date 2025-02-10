package repository.impl;

import models.Board;
import repository.BoardRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardRepositoryImpl implements BoardRepository {
    Map<Integer, Board> boardMap = new HashMap<>();
    @Override
    public void save(Board board) {
        boardMap.put(board.getBoardId(), board);
    }

    @Override
    public Optional<Board> getBoardById(Integer boardId) {
        return Optional.ofNullable(boardMap.get(boardId));
    }
}

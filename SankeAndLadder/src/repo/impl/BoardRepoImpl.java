package repo.impl;

import models.Board;
import repo.BoardRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BoardRepoImpl implements BoardRepo {
    private Map<Integer, Board> boardByIdMap = new HashMap<>();
    @Override
    public void save(Board board) {
        boardByIdMap.put(board.getBoardId(), board);
    }

    @Override
    public void update(Board board) {
      boardByIdMap.put(board.getBoardId(), board);
    }

    @Override
    public Optional<Board> findBoardBYId(Integer id) {
        return Optional.ofNullable(boardByIdMap.get(id));
    }
}

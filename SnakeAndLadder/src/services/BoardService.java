package services;

import constant.BoardConstant;
import models.*;
import repository.BoardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service responsible for board-related operations.
 */
public class BoardService {
    private final BoardRepository boardRepository;
    private final AtomicInteger boardIdGenerator;

    public BoardService(BoardRepository boardRepository, AtomicInteger boardIdGenerator) {
        this.boardRepository = boardRepository;
        this.boardIdGenerator = boardIdGenerator;
    }

    /**
     * Creates a new board with the specified snake and ladder configurations.
     *
     * @param snakes  A map where keys are starting positions of snakes and values are their tail positions.
     * @param ladders A map where keys are starting positions of ladders and values are their end positions.
     * @return The created Board.
     */
    public Board createBoard(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        validateBoardConfiguration(snakes, ladders);
        final List<Cell> cells = new ArrayList<>();
        for (int position = 1; position <= BoardConstant.MAX_ALLOWED_CELL_POSITION; position++) {
            cells.add(createCell(position, snakes, ladders));
        }
        Board board = new Board(boardIdGenerator.getAndIncrement(), cells);
        boardRepository.save(board);
        return board;
    }

    /**
     * Checks if a given cell is the winning position.
     *
     * @param cell The cell to check.
     * @return True if the cell is the winning position, false otherwise.
     */
    public boolean isWinningPosition(Cell cell) {
        return cell.getPosition().equals(BoardConstant.WINNING_POSITION);
    }

    /**
     * Retrieves a board by its ID.
     *
     * @param boardId The ID of the board.
     * @return The Board if found.
     * @throws IllegalArgumentException if the board is not found.
     */
    public Board getBoardById(Integer boardId) {
        return boardRepository.getBoardById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found for ID: " + boardId));
    }

    /**
     * Creates a cell at the given position using the provided snake and ladder configurations.
     *
     * @param position The position of the cell.
     * @param snakes   Map of snake starting positions to their tail positions.
     * @param ladders  Map of ladder starting positions to their end positions.
     * @return The corresponding Cell instance.
     */
    private Cell createCell(int position, Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        if (snakes.containsKey(position)) {
            return new SnakeCell(position, String.valueOf(position), snakes.get(position));
        } else if (ladders.containsKey(position)) {
            return new LadderCell(position, String.valueOf(position), ladders.get(position));
        } else {
            return new NormalCell(position, String.valueOf(position));
        }
    }
    public static void validateBoardConfiguration(Map<Integer, Integer> snakes, Map<Integer, Integer> ladders) {
        // Validate snake configuration
        for (Map.Entry<Integer, Integer> entry : snakes.entrySet()) {
            int head = entry.getKey();
            int tail = entry.getValue();
            if (head <= tail) {
                throw new IllegalArgumentException("Invalid snake configuration: snake head " + head +
                        " should be greater than tail " + tail);
            }
            if (head < 1 || head > BoardConstant.MAX_ALLOWED_CELL_POSITION ||
                    tail < 1 || tail > BoardConstant.MAX_ALLOWED_CELL_POSITION) {
                throw new IllegalArgumentException("Snake positions must be between 1 and " + BoardConstant.MAX_ALLOWED_CELL_POSITION);
            }
        }

        // Validate ladder configuration
        for (Map.Entry<Integer, Integer> entry : ladders.entrySet()) {
            int start = entry.getKey();
            int end = entry.getValue();
            if (start >= end) {
                throw new IllegalArgumentException("Invalid ladder configuration: ladder start " + start +
                        " should be less than end " + end);
            }
            if (start < 1 || start > BoardConstant.MAX_ALLOWED_CELL_POSITION ||
                    end < 1 || end > BoardConstant.MAX_ALLOWED_CELL_POSITION) {
                throw new IllegalArgumentException("Ladder positions must be between 1 and " + BoardConstant.MAX_ALLOWED_CELL_POSITION);
            }
        }
    }
}

package services;

import models.*;
import repo.BoardRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BoardService {
    private final AtomicInteger boardIdGenerator;
    private final BoardRepo boardRepo;

    public BoardService(AtomicInteger boardIdGenerator, BoardRepo boardRepo) {
        this.boardIdGenerator = boardIdGenerator;
        this.boardRepo = boardRepo;
    }

    public Board createBoard(Map<Integer, Integer>snakes, Map<Integer, Integer> ladders, int totalTile){
        validateCreateBoardRequest(snakes, ladders, totalTile);
        List<Tile>tiles = createTiles(snakes,ladders, totalTile);
        Board board = new Board(boardIdGenerator.getAndIncrement(), tiles);
        boardRepo.save(board);
        return board;
    }

    private void validateCreateBoardRequest(Map<Integer, Integer>snakes, Map<Integer, Integer> ladders, int totalTile){

        for(Map.Entry<Integer, Integer> entry: snakes.entrySet() ){
            Integer from = entry.getKey();
            Integer to = entry.getValue();
            if(from >= totalTile || to > totalTile){
                throw new RuntimeException("pos of snake should be less than number of tiles");
            }
            if(from <= to){
                throw new RuntimeException("Snake pos are not valid");
            }
            if(ladders.containsKey(from)){
                throw new RuntimeException("Ladder should be not present at snake position");
            }
        }

        for(Map.Entry<Integer, Integer> entry: ladders.entrySet() ){
            Integer from = entry.getKey();
            Integer to = entry.getValue();
            if(from > totalTile || to > totalTile){
                throw new RuntimeException("ladder pos invalid found");
            }
            if(from >= to){
                throw new RuntimeException("ladder should take up not down. ");
            }
            if(snakes.containsKey(from)){
                throw new RuntimeException("Ladder should be not present at snake position");
            }
        }
    }

    private List<Tile>createTiles(Map<Integer, Integer>snakes, Map<Integer, Integer> ladders, int totalTile){
       List<Tile> tiles = new ArrayList<>();
       for(int i=1; i<=totalTile; i++){
           tiles.add(createTile(i, snakes, ladders));
       }
       return tiles;
    }

    private Tile createTile(Integer position, Map<Integer, Integer>snakes, Map<Integer, Integer> ladders){
        if(snakes.containsKey(position)){
            return new SnakeTile(position, snakes.get(position));
        }
        if(ladders.containsKey(position)){
            return new LadderTile(position, ladders.get(position));
        }
        return new PlainTile(position);
    }

    public Board findBoardById(Integer boardId) {
        return boardRepo.findBoardBYId(boardId).orElseThrow();
    }
}

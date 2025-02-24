package repo;
import java.util.*;
import models.*;

public interface BoardRepo {
    public void save(Board board);
    public void update(Board board);
    public Optional<Board>findBoardBYId(Integer id);
}

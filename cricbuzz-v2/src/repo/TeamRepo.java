package repo;
import java.util.*;
import models.*;
public interface TeamRepo {
    public void save(Team team);
    public Optional<Team>getById(Long id);
    public List<Team>getTeams(List<Long>teamIds);
}

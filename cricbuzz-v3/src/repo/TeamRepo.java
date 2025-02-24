package repo;
import java.util.*;
import models.*;
public interface TeamRepo {
    public void save(Team team);
    public Optional<Team>findTeamById(Long id);
    public List<Team>findTeams(List<Long>teamIds);
}

package repo;
import models.*;
import java.util.*;
public interface TeamRepo {
    public void save(Team team);
    public Optional<Team>findTeamById(Long id);
    public List<Team>findTeamsById(List<Long>teamIds);
}

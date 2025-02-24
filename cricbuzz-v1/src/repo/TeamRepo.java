package repo;
import java.util.*;
import models.*;

public interface TeamRepo {
    public void save(Team team);
    public Optional<Team>getTeamById(Integer team);
    public void update(Team team);

    List<Team> getTeams(List<Integer> teamIds);
}

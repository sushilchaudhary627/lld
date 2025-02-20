package repository;

import models.Team;

import java.util.Optional;

public interface TeamRepository {
    void save(Team team);
    Optional<Team> getTeamById(Integer teamId);
}

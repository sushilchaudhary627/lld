package repository;

import models.Aircraft;

import java.util.Optional;

public interface AircraftRepository {
    public void save(Aircraft aircraft);
    public Optional<Aircraft> getAircraftByName(String name);
}

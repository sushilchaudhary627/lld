package repository;

import models.Building;

import java.util.Optional;

public interface BuildingRepository {
    public void save(Building building);
    public Optional<Building> getBuildingById(Integer id);

    void update(Building building);
}

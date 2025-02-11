package repository.impl;

import models.Building;
import repository.BuildingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BuildingRepositoryImpl implements BuildingRepository {
    Map<Integer, Building> buildingMap = new HashMap<>();
    @Override
    public void save(Building building) {
        buildingMap.put(building.getBuildingId(), building);
    }

    @Override
    public Optional<Building> getBuildingById(Integer id) {
        return Optional.ofNullable(buildingMap.get(id));
    }

    @Override
    public void update(Building building) {

    }
}

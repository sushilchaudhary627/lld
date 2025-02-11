package services;

import models.Building;
import models.Floor;
import models.Lift;
import repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BuildingService {
    private final BuildingRepository buildingRepository;
    private final AtomicInteger buildingIdGenerator;
    private final AtomicInteger liftIdGenerator;

    public BuildingService(BuildingRepository buildingRepository, AtomicInteger buildingIdGenerator, AtomicInteger liftIdGenerator) {
        this.buildingRepository = buildingRepository;
        this.buildingIdGenerator = buildingIdGenerator;
        this.liftIdGenerator = liftIdGenerator;
    }

    /**
     * Creates a new building with the given name and floor numbers.
     *
     * @param name         The name of the building.
     * @param floorNumbers A list of floor numbers for the building.
     * @return The created Building.
     */
    public Building addBuilding(String name, List<Integer> floorNumbers) {
        Building building = new Building(buildingIdGenerator.getAndIncrement(), name);
        List<Floor> floors = new ArrayList<>();
        for (Integer floorNo : floorNumbers) {
            floors.add(new Floor(floorNo));
        }
        building.setFloors(floors);
        buildingRepository.save(building);
        return building;
    }

    /**
     * Adds a new lift to the specified building.
     *
     * @param buildingId  The ID of the building.
     * @param maxCapacity The maximum capacity of the lift.
     * @return The updated Building.
     */
    public Building addLiftToBuilding(Integer buildingId, Integer maxCapacity) {
        Building building = buildingRepository.getBuildingById(buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Building not found with id: " + buildingId));

        // Ensure the building has at least one floor.
        if (building.getFloors() == null || building.getFloors().isEmpty()) {
            throw new IllegalStateException("Building with id " + buildingId + " has no floors defined.");
        }

        // Use get(0) to retrieve the first floor.
        Lift lift = new Lift(liftIdGenerator.getAndIncrement(), building.getFloors().get(0), maxCapacity);
        building.addNewLift(lift);
        buildingRepository.update(building);
        return building;
    }
}

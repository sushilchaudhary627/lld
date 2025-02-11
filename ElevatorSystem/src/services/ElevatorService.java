package services;

import models.Building;
import models.Floor;
import models.Lift;
import strategy.LiftSelectionStrategy;
import repository.BuildingRepository;

import java.util.Optional;

public class ElevatorService {
    private final LiftSelectionStrategy liftSelectionStrategy;
    private final BuildingRepository buildingRepository;

    public ElevatorService(LiftSelectionStrategy liftSelectionStrategy, BuildingRepository buildingRepository) {
        this.liftSelectionStrategy = liftSelectionStrategy;
        this.buildingRepository = buildingRepository;
    }

    public Lift getLiftForFloor(Integer buildingId, Integer currentFloorNo) {
        Building building = buildingRepository.getBuildingById(buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Building not found with id: " + buildingId));

        Floor currentFloor = building.getFloors().stream()
                .filter(floor -> floor.getFloorNo().equals(currentFloorNo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Current floor not found: " + currentFloorNo));

        Optional<Lift> availableLift = liftSelectionStrategy.getLiftForFloor(currentFloor, building.getLifts());
        Lift lift = availableLift.orElseThrow(() -> new IllegalStateException("No available lift found"));

        // The lift itself now determines how to move.
        lift.moveTo(currentFloor);  // Move the lift to pick up passengers.
        return lift;
    }

    public Lift goToFloor(Integer buildingId, Integer liftId, Integer floorNo) {
        Building building = buildingRepository.getBuildingById(buildingId)
                .orElseThrow(() -> new IllegalArgumentException("Building not found with id: " + buildingId));

        Lift lift = building.getLifts().stream()
                .filter(l -> l.getId().equals(liftId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Lift not found with id: " + liftId));

        Floor floor = building.getFloors().stream()
                .filter(f -> f.getFloorNo().equals(floorNo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Floor not found: " + floorNo));

        lift.moveTo(floor);
        buildingRepository.update(building);
        return lift;
    }
}

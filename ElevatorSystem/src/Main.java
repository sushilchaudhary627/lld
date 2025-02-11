import models.Building;
import models.Lift;
import repository.BuildingRepository;
import repository.impl.BuildingRepositoryImpl;
import services.BuildingService;
import services.ElevatorService;
import strategy.impl.LiftSelectionStrategyImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BuildingRepository buildingRepository = new BuildingRepositoryImpl();
        BuildingService buildingService  = new BuildingService(buildingRepository, new AtomicInteger(1), new AtomicInteger(1));
        Building building = buildingService.addBuilding("Building Aaa", List.of(0,1,2,3,4));
        System.out.println(building);
        buildingService.addLiftToBuilding(building.getBuildingId(), 20);
        buildingService.addLiftToBuilding(building.getBuildingId(), 20);
        buildingService.addLiftToBuilding(building.getBuildingId(), 20);
        ElevatorService elevatorService = new ElevatorService(new LiftSelectionStrategyImpl(), buildingRepository);
        Lift lift = elevatorService.getLiftForFloor(building.getBuildingId(), 0);
        System.out.println(lift);
        elevatorService.goToFloor(building.getBuildingId(), lift.getId(), 4);
        System.out.println(lift);
    }
}


// Design elevator
//  goTo(current floor, destination floor)
// Option - lift is going up and can be chosen for current floor
// lift capacity - max weight
// lets 2 lift option
// multiple people requested from same floor ?
// multiple people requested ?


// entity
// Lift - id
// Floor
// Configuration
// LiftSelectionStrategy take list of available lifts anf give one life for people requesting from floorNo

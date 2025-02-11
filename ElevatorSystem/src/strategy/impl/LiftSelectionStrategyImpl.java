package strategy.impl;

import models.Floor;
import models.Lift;
import strategy.LiftSelectionStrategy;

import java.util.List;
import java.util.Optional;

public class LiftSelectionStrategyImpl implements LiftSelectionStrategy {

    @Override
    public Optional<Lift> getLiftForFloor(Floor requestedFloor, List<Lift> lifts) {
        Lift bestLift = null;
        int minDistance = Integer.MAX_VALUE;
        int reqFloorNo = requestedFloor.getFloorNo();

        for (Lift lift : lifts) {
            String state = lift.getState().getStateName().toUpperCase();
            int currentFloorNo = lift.getCurrentFloor().getFloorNo();
            boolean eligible = false;

            // Determine eligibility:
            // 1. If the lift is IDLE, it is always eligible.
            if ("IDLE".equals(state)) {
                eligible = true;
            }
            // 2. If the lift is moving up and the requested floor is at or above its current floor.
            else if ("MOVING_UP".equals(state) && reqFloorNo >= currentFloorNo) {
                eligible = true;
            }
            // 3. If the lift is moving down and the requested floor is at or below its current floor.
            else if ("MOVING_DOWN".equals(state) && reqFloorNo <= currentFloorNo) {
                eligible = true;
            }

            if (eligible) {
                int distance = Math.abs(currentFloorNo - reqFloorNo);
                if (distance < minDistance) {
                    minDistance = distance;
                    bestLift = lift;
                }
            }
        }

        return Optional.ofNullable(bestLift);
    }
}

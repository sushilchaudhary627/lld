package strategy;

import models.Floor;
import models.Lift;

import java.util.List;
import java.util.Optional;

public interface LiftSelectionStrategy {
    Optional<Lift> getLiftForFloor(Floor currentFloor, List<Lift> lifts);
}


package models.states;

import models.Floor;
import models.Lift;
import models.LiftState;

public class MovingUpState implements LiftState {
    @Override
    public void moveTo(Lift lift, Floor destination) {
        System.out.println("Lift " + lift.getId() + " is moving up from floor "
                + lift.getCurrentFloor().getFloorNo() + " to floor " + destination.getFloorNo());
        // Simulate movement (in a real system, you might have asynchronous operations here)
        lift.setCurrentFloor(destination);
        // After reaching the destination, return to Idle state.
        lift.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "MOVING_UP";
    }
}
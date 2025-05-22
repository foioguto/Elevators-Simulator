package run.panel;

import run.model.Building;
import run.model.Elevator;
import run.model.Floor;
import run.model.User;

/**
 * Handles the external elevator panel logic for floor call buttons
 * Determines when users want to enter the elevator based on direction
 */
public class ExternalPanel {

    // ==================== Constructor ====================

    /**
     * Creates a new external panel instance
     */
    public ExternalPanel() {
        // Initialization if needed
    }

    // ==================== Panel Logic ====================

    /**
     * Checks if any users on this floor want to enter the elevator
     * @param floor The current floor where panel is located
     * @param building The building containing all floors
     * @param elevator The elevator being called
     * @return true if users want to board, false otherwise
     */
    public boolean wantsToEnterHere(Floor floor, Building building, Elevator elevator) {
        for (User user : floor.getUsers()) {
            if (user != null) {
                // Check various boarding conditions
                if (isBoardingConditionMet(user, elevator, building)) {
                    return true;
                }
            }
        }
        return false;
    }

    // ==================== Helper Methods ====================

    /**
     * Determines if boarding conditions are met for a user
     * @param user The potential passenger
     * @param elevator The current elevator state
     * @param building The building context
     * @return true if conditions are met for boarding
     */
    private boolean isBoardingConditionMet(User user, Elevator elevator, Building building) {
        Elevator.ElevatorState state = elevator.getState();
        int currentFloor = elevator.getCurrentFloor();
        int topFloor = building.getTotalFloors() - 1;

        return state == Elevator.ElevatorState.IDLE ||
                (state == Elevator.ElevatorState.UP && user.isUp()) ||
                (state == Elevator.ElevatorState.DOWN && !user.isUp()) ||
                (currentFloor == topFloor && !user.isUp()) ||
                (currentFloor == 0 && user.isUp());
    }
}
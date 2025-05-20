package config.heuristic;

import run.Elevator;
import run.Building;
import run.Elevator.ElevatorState;
import run.dataStructure.UserQueue;
import run.panels.InternalPanel;

public class EconomyHeuristic implements MovementStyle {
    private InternalPanel intPanel;

    public EconomyHeuristic() {
        intPanel = new InternalPanel();
    }

    @Override
    public int decideNextFloor(Elevator elevator, Building building) {
        int currentFloor = elevator.getCurrentFloor();
        ElevatorState currentState = elevator.getState();
        int direction = currentState.getDirectionCode();
        
        // If idle, start moving up by default
        if (currentState == ElevatorState.IDLE) {
            direction = ElevatorState.UP.getDirectionCode();
        }
        
        // Check if we should continue in current direction
        if (!hasRequestsInCurrentDirection(building, elevator, direction)) {
            // If no requests in current direction, check opposite direction
            if (hasRequestsInOppositeDirection(building, elevator, direction)) {
                direction = -direction; // reverse direction
            } else {
                return currentFloor; // no requests anywhere, stay put
            }
        }
        
        int nextFloor = currentFloor + direction;
        
        // Handle boundary conditions
        if (nextFloor >= building.getTotalFloors()) {
            nextFloor = building.getTotalFloors() - 1;
            direction = -1; // start going down
        } else if (nextFloor < 0) {
            nextFloor = 0;
            direction = 1; // start going up
        }
        
        // Update elevator state based on new direction
        elevator.setState(direction > 0 ? ElevatorState.UP : ElevatorState.DOWN);
        
        return nextFloor;
    }

    private boolean hasRequestsInCurrentDirection(Building building, Elevator elevator, int direction) {
        int currentFloor = elevator.getCurrentFloor();
        UserQueue currentUsers = elevator.getCurrentUsers();

        return (direction > 0 && (elevator.requestsAbove(building) || intPanel.insideWantsToGoUp(currentUsers, currentFloor))) ||
               (direction < 0 && (elevator.requestsBelow(building) || intPanel.insideWantsToGoDown(currentUsers, currentFloor)));
    }

    private boolean hasRequestsInOppositeDirection(Building building, Elevator elevator, int direction) {
        int currentFloor = elevator.getCurrentFloor();
        UserQueue currentUsers = elevator.getCurrentUsers();

        return (direction > 0 && (elevator.requestsBelow(building) || intPanel.insideWantsToGoDown(currentUsers, currentFloor))) ||
               (direction < 0 && (elevator.requestsAbove(building) || intPanel.insideWantsToGoUp(currentUsers, currentFloor)));
    }
}
package config.heuristic;

import run.Elevator;
import run.Building;
import run.UserQueue;
import run.InternalPanel;
import run.Elevator.ElevatorState;

public class EconomyHeuristic implements MovementStyle {
    private InternalPanel intPanel;

    public EconomyHeuristic() {
        intPanel = new InternalPanel();
    }

    @Override
    public int decideNextFloor(Elevator elevator, Building building) {
        int currentFloor = elevator.getCurrentFloor();
        int directionCode = elevator.getState().getDirectionCode();

        if (elevator.getState() == ElevatorState.IDLE) {
            directionCode = ElevatorState.UP.getDirectionCode();
        } 
        if (!hasRequestsInCurrentDirection(building, elevator, directionCode)) {
            if (hasRequestsInOppositeDirection(building, elevator, directionCode)) {
                directionCode = -directionCode;
            } else {
                elevator.stopElevator();
            }
        }

        int nextFloor = currentFloor += directionCode;

        if (nextFloor == building.getTotalFloors() - 1) {
            nextFloor = currentFloor --;
        } else if (nextFloor < 0) {
            nextFloor = currentFloor++;
        }
        
        return nextFloor;
    }

    private boolean hasRequestsInCurrentDirection(Building building, Elevator elevator, int directionCode) {
        int currentFloor = elevator.getCurrentFloor();
        UserQueue currentUsers = elevator.getCurrentUsers();

        return (directionCode > 0 && (elevator.requestsAbove(building) || intPanel.insideWantsToGoUp(currentUsers, currentFloor))) ||
                (directionCode < 0 && (elevator.requestsBelow(building) || intPanel.insideWantsToGoDown(currentUsers, currentFloor)));
    }

    private boolean hasRequestsInOppositeDirection(Building building, Elevator elevator, int directionCode) {
        int currentFloor = elevator.getCurrentFloor();
        UserQueue currentUsers = elevator.getCurrentUsers();

        return (directionCode > 0 && (elevator.requestsBelow(building) || intPanel.insideWantsToGoDown(currentUsers, currentFloor))) ||
                (directionCode < 0 && (elevator.requestsAbove(building) || intPanel.insideWantsToGoUp(currentUsers, currentFloor)));
    }

}


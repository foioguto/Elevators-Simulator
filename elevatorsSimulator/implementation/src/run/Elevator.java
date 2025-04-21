package run;

import dataStructure.InternalPanel;
import dataStructure.List;
import dataStructure.Vector;

/**
 * Represents an elevator in a building simulation.
 * Manages elevator movement, user entry/exit, and direction requests.
 */
public class Elevator extends InternalPanel {
    private final int maxCapacity;
    private ElevatorState state;
    private int currentFloor;
    private boolean moving;
    private List currentUsers = new List();
    private int sleepMode = 0;

    /**
     * Constants for the elevator.
     */
    public enum ElevatorState {
        IDLE(0),
        UP(1),
        DOWN(-1);

        private final int directionCode;

        ElevatorState(int directionCode) {
            this.directionCode = directionCode;
        }

        public int getDirectionCode;

    }

    public Elevator(int maxCapacity) {
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.moving = false;
        this.maxCapacity = maxCapacity;
    }
    /**
     * Moves the elevator up one floor.
     * Updates the current floor and direction state.
     */
    public void moveUp(Building building) {
        InternalPanel panel = new InternalPanel();
        state = ElevatorState.UP;
        boolean keepGoing = true;
        if (checkSleepMode()){
            stop();
            keepGoing = false;
        }else {
            keepGoing = true;
        }
        while (keepGoing) {
            if(wantsToEnterHere(building.getFloor(currentFloor)) || panel.wantsToExitHere(building.getFloor(currentFloor).getFloor())){
                handleDoorsAtCurrentFloor(building.getFloor(currentFloor));
            }else{
                if(requestsAbove(building) || panel.insideWantsToGoUp()){
                    return;
                }else{
                    keepGoing = false;
                    sleepMode += 1;
                    moveDown(building);
                    break;
                }
            }
            currentFloor++;
        }
    }

    /**
     * Moves the elevator down one floor.
     * Updates the current floor and direction state.
     */
    public void moveDown(Building building) {
        InternalPanel panel = new InternalPanel();
        state = ElevatorState.DOWN;
        boolean keepGoing = true;
        if (checkSleepMode()){
            stop();
            keepGoing = false;
        }else {
            keepGoing = true;
        }
        while (keepGoing) {
            if(wantsToEnterHere(building.getFloor(currentFloor)) || panel.wantsToExitHere(building.getFloor(currentFloor).getFloor())){
                handleDoorsAtCurrentFloor(building.getFloor(currentFloor));
            }else{
                if(requestsBelow(building) || panel.insideWantsToGoDown()){
                    return;
                }else{
                    keepGoing = false;
                    sleepMode += 1;
                    moveUp(building);
                    break;
                }
            }
            currentFloor--;
        }
    }

    public boolean checkSleepMode(){
        if(sleepMode == 2){
            return true;
        }else{
            return false;
        }
    }

    public void stop() {
        state = ElevatorState.IDLE;
    }

    /**
     * Checks if there are users waiting to go up on the specified floor.
     * @param vector The floor to check for waiting users
     * @return true if at least one user wants to go up, false otherwise
     */
    public boolean wantsToEnterHere(Vector vector) {
        boolean ok = false;
        for(int i = 0; i < vector.getUsers().length; i++) {
            if(vector.getUser(i).isUp()) {
                ok = true;
            }
        }
        return ok;
    }

    /**
     * Handles door operations at the current floor.
     * Manages the opening/closing of doors and passenger flow.
     */
    public void handleDoorsAtCurrentFloor(Vector vector) {
        currentUsers.detectExitRequests(this.currentFloor);
        vector.getEveryoneInside(this.currentUsers);
    }

    /**
     * Checks if there are any requests for elevator service above the current floor.
     * @param building The building to check for requests
     * @return true if there are requests above, false otherwise
     */
    public boolean requestsAbove(Building building) {
        boolean ok = false;
        for (int i = 0; i < building.getFloors().length; i++) {
            if(i > currentFloor) {
                if (building.getFloor(i).getUser(i) != null) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    /**
     * Checks if there are any requests for elevator service below the current floor.
     * @param building The building to check for requests
     * @return true if there are requests below, false otherwise
     */
    public boolean requestsBelow(Building building) {
        boolean ok = false;
        for (int i = 0; i < building.getFloors().length; i++) {
            if(i < currentFloor) {
                if (building.getFloor(i).getUser(i) != null) {
                    ok = true;
                }
            }
        }
        return ok;
    }

    // Getters and Setters

    /**
     * Gets the current floor of the elevator.
     * @return The current floor number
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Sets the current floor of the elevator.
     * @param currentFloor The new current floor number
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Gets the queue of current passengers in the elevator.
     * @return The Queue object containing current passengers
     */
    public List getCurrentUsers() {
        return currentUsers;
    }

    /**
     * Sets the queue of current passengers in the elevator.
     * @param currentUsers The new Queue of passengers
     */
    public void setCurrentUsers(List currentUsers) {
        this.currentUsers = currentUsers;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

}

package run;

import dataStructure.InternalPanel;
import dataStructure.DoubleLinkedList;
import dataStructure.Floor;

/**
 * Represents an elevator in a building simulation.
 * Manages elevator movement, user entry/exit, and direction requests.
 */
public class Elevator extends InternalPanel {
    private final int maxCapacity;
    private ElevatorState state;
    private int currentFloor;
    private boolean moving;
    private DoubleLinkedList currentUsers = new DoubleLinkedList();
    private int sleepMode = 0;
    InternalPanel panel = new InternalPanel();

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
        state = ElevatorState.UP;
        boolean keepGoing = true;

        if (checkSleepMode()) {
            stop();
            return;
        }

        while (keepGoing && currentFloor < building.getTotalFloors()) {

            Floor floor = building.getFloor(currentFloor);

            boolean wantsToEnter = wantsToEnterHere(floor);
            boolean wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);
            boolean someoneAbove = requestsAbove(building);
            boolean insideWantsUp = panel.insideWantsToGoUp(currentUsers, currentFloor);

            if (wantsToEnter || wantsToExit) {
                handleDoorsAtCurrentFloor(floor);

                wantsToEnter = wantsToEnterHere(floor);
                wantsToExit = panel.wantsToExitHere(currentUsers,currentFloor);
                someoneAbove = requestsAbove(building);
                insideWantsUp = panel.insideWantsToGoUp(currentUsers, currentFloor);

                if (!wantsToEnter && !wantsToExit && !someoneAbove && !insideWantsUp) {
                    sleepMode++;
                    moveDown(building);
                    break;
                }
            } else if (!someoneAbove && !insideWantsUp) {
                sleepMode++;
                moveDown(building);
                break;
            }

            currentFloor++;
        }
    }



    /**
     * Moves the elevator down one floor.
     * Updates the current floor and direction state.
     */
    public void moveDown(Building building) {
        state = ElevatorState.DOWN;
        boolean keepGoing = true;

        if (checkSleepMode()) {
            stop();
            return;
        }

        while (keepGoing && currentFloor >= 0) {

            Floor floor = building.getFloor(currentFloor);

            boolean wantsToEnter = wantsToEnterHere(floor);
            boolean wantsToExit = panel.wantsToExitHere(currentUsers,currentFloor);
            boolean someoneBelow = requestsBelow(building);
            boolean insideWantsDown = panel.insideWantsToGoDown(currentUsers, currentFloor);

            if (wantsToEnter || wantsToExit) {
                handleDoorsAtCurrentFloor(floor);

                wantsToEnter = wantsToEnterHere(floor);
                wantsToExit = panel.wantsToExitHere(currentUsers,currentFloor);
                someoneBelow = requestsBelow(building);
                insideWantsDown = panel.insideWantsToGoDown(currentUsers, currentFloor);

                if (!wantsToEnter && !wantsToExit && !someoneBelow && !insideWantsDown) {
                    sleepMode++;
                    moveUp(building);
                    break;
                }
            } else if (!someoneBelow && !insideWantsDown) {
                sleepMode++;
                moveUp(building);
                break;
            }

            currentFloor--;
        }
    }


    public boolean checkSleepMode() {
        return sleepMode == 2;
    }

    public void resetSleepMode() {
        this.sleepMode = 0;
    }

    public void stop() {
        state = ElevatorState.IDLE;
    }

    /**
     * Checks if there are users waiting to go up on the specified floor.
     * @param floor The floor to check for waiting users
     * @return true if at least one user wants to go up, false otherwise
     */
    public boolean wantsToEnterHere(Floor floor) {
        for (int i = 0; i < floor.getUsers().length; i++) {
            User user = floor.getUser(i);
            if (user != null) {
                if (this.state == ElevatorState.UP && user.isUp()) {
                    return true;
                }
                if (this.state == ElevatorState.DOWN && !user.isUp()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Handles door operations at the current floor.
     * Manages the opening/closing of doors and passenger flow.
     */
    public void handleDoorsAtCurrentFloor(Floor floor) {
        currentUsers.detectExitRequests(this.currentFloor);
        floor.getEveryoneInside(this.currentUsers);
    }

    /**
     * Checks if there are any requests for elevator service above the current floor.
     * @param building The building to check for requests
     * @return true if there are requests above, false otherwise
     */
    public boolean requestsAbove(Building building) {
        for (int i = currentFloor + 1; i < building.getFloors().length; i++) {
            User[] users = building.getFloor(i).getUsers();
            if (users != null) {
                for (User user : users) {
                    if (user != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if there are any requests for elevator service below the current floor.
     * @param building The building to check for requests
     * @return true if there are requests below, false otherwise
     */
    public boolean requestsBelow(Building building) {
        for (int i = 0; i < building.getFloors().length; i++) {
            if (i < currentFloor) {
                Floor floor = building.getFloor(i);
                User[] users = floor.getUsers();

                if (users != null) {
                    for (User user : users) {
                        if (user != null) {
                            if (!user.isUp()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
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
    public DoubleLinkedList getCurrentUsers() {
        return currentUsers;
    }

    /**
     * Sets the queue of current passengers in the elevator.
     * @param currentUsers The new Queue of passengers
     */
    public void setCurrentUsers(DoubleLinkedList currentUsers) {
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

package run;

import dataStructure.InternalPanel;
import dataStructure.DoubleLinkedList;
import dataStructure.Floor;

/**
 * Represents an elevator in a building simulation.
 * Manages elevator movement, user entry/exit, and direction requests.
 */
public class Elevator extends DoubleLinkedList {
    private final int maxCapacity;
    private ElevatorState state;
    private int currentFloor;
    private int sleepMode = 0;
    private DoubleLinkedList currentUsers;
    private InternalPanel panel;
    private boolean priority; // the elevator is for priority queue or not
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

        public int getDirectionCode() {
            return directionCode;
        }
    }

    public Elevator(int maxCapacity, boolean priority) {
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.maxCapacity = maxCapacity;
        this.priority = priority;
        this.currentUsers = new DoubleLinkedList();
        this.panel = new InternalPanel();
    }

    /**
     * Moves the elevator up one floor.
     * Updates the current floor and direction state.
     */
    public void moveUp(Building building) {
        if (checkSleepMode(building)) {
            stop();
            return;
        }

        while (currentFloor < building.getTotalFloors()) {
            state = ElevatorState.UP;
            Floor floor = building.getFloor(currentFloor);
            System.out.println("Current Floor: " + currentFloor + ", Current User: " + currentUsers.getSize() + ", State: " + state);

            boolean wantsToEnter = wantsToEnterHere(floor);
            boolean wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);
            boolean someoneAbove = requestsAbove(building);
            boolean insideWantsUp = panel.insideWantsToGoUp(currentUsers, currentFloor);

            if (wantsToEnter || wantsToExit) {
                state = ElevatorState.IDLE;
                handleDoorsAtCurrentFloor(floor);

                wantsToEnter = wantsToEnterHere(floor);
                wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);
                someoneAbove = requestsAbove(building);
                insideWantsUp = panel.insideWantsToGoUp(currentUsers, currentFloor);

                if (!wantsToEnter && !wantsToExit && !someoneAbove && !insideWantsUp) {
                    System.out.println("Current Floor: " + currentFloor + ", Current User: " + currentUsers.getSize() + ", State: " + state);
                    moveDown(building);
                    break;
                }
            } else if (!someoneAbove && !insideWantsUp) {
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
        if (checkSleepMode(building)) {
            stop();
            return;
        }

        while (currentFloor >= 0) {
            state = ElevatorState.DOWN;
            Floor floor = building.getFloor(currentFloor);
            System.out.println("Current Floor: " + currentFloor + ", Current User: " + currentUsers.getSize() + ", State: " + state);

            boolean wantsToEnter = wantsToEnterHere(floor);
            boolean wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);
            boolean someoneBelow = requestsBelow(building);
            boolean insideWantsDown = panel.insideWantsToGoDown(currentUsers, currentFloor);

            if (wantsToEnter || wantsToExit) {
                handleDoorsAtCurrentFloor(floor);
                state = ElevatorState.IDLE;

                wantsToEnter = wantsToEnterHere(floor);
                wantsToExit = panel.wantsToExitHere(currentUsers, currentFloor);
                someoneBelow = requestsBelow(building);
                insideWantsDown = panel.insideWantsToGoDown(currentUsers, currentFloor);

                if (!wantsToEnter && !wantsToExit && !someoneBelow && !insideWantsDown) {
                    System.out.println("Current Floor: " + currentFloor + ", Current User: " + currentUsers.getSize() + ", State: " + state);
                    moveUp(building);
                    break;
                }
            } else if (!someoneBelow && !insideWantsDown) {
                moveUp(building);
                break;
            }

            currentFloor--;
        }
    }

    /**
     * Checks if the elevator should enter sleep mode.
     * @return true if sleep mode is activated
     */
    public boolean checkSleepMode(Building building) {
        return !requestsAbove(building) && !requestsBelow(building) && !requestsHere(building) && currentUsers.getSize() == 0;
    }

    /**
     * Resets the elevator's sleep mode counter.
     */
    public void resetSleepMode() {
        this.sleepMode = 0;
    }

    /**
     * Stops the elevator and sets it to IDLE.
     */
    public void stop() {
        state = ElevatorState.IDLE;
    }

    /**
     * Checks if there are users waiting to go in the elevator's current direction.
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
     */
    public void handleDoorsAtCurrentFloor(Floor floor) {
        currentUsers.detectExitRequests(this.currentFloor);
        floor.goToElevator((Elevator) this.currentUsers);
    }

    public boolean requestsHere(Building building) {
        User[] users = building.getFloor(currentFloor).getUsers();
            if (users != null) {
                for (User user : users) {
                    if (user != null) {
                        return true;
                    }
                }
            }
        return false;
    }

    /**
     * Checks for any user requests above the current floor.
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
     * Checks for any user requests below the current floor.
     */
    public boolean requestsBelow(Building building) {
        for (int i = 0; i < currentFloor; i++) {
            User[] users = building.getFloor(i).getUsers();
            if (users != null) {
                for (User user : users) {
                    if (user != null && !user.isUp()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    // Getters and Setters

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public DoubleLinkedList getCurrentUsers() {
        return currentUsers;
    }

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

    public boolean isPriority() {return priority;}

    public void setPriority(boolean priority) {this.priority = priority;}
}

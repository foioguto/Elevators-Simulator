package run;

import dataStructure.Queue;

/**
 * Represents an elevator in a building simulation.
 * Manages elevator movement, user entry/exit, and direction requests.
 */
public class Elevator {
    private int currentFloor;
    private boolean up;
    private boolean down;
    private boolean moving;
    private Queue currentUsers = new Queue();

    /**
     * Moves the elevator up one floor.
     * Updates the current floor and direction state.
     */
    public void moveUp() {
        // Implementation to move elevator up
    }

    /**
     * Moves the elevator down one floor.
     * Updates the current floor and direction state.
     */
    public void moveDown() {
        // Implementation to move elevator down
    }

    /**
     * Checks if there are users waiting to go up on the specified floor.
     * @param floor The floor to check for waiting users
     * @return true if at least one user wants to go up, false otherwise
     */
    public boolean wantsToEnterHereUp(Floor floor) {
        boolean ok = false;
        for(int i = 0; i < floor.getUsers().length; i++) {
            if(floor.getUser(i).isUp()) {
                ok = true;
            }
        }
        return ok;
    }

    /**
     * Checks if any current passengers want to exit at the current floor.
     * @return true if at least one passenger wants to exit, false otherwise
     */
    public boolean wantsToExitHere() {
        return currentUsers.wantsToExitHere(currentFloor);
    }

    /**
     * Handles door operations at the current floor.
     * Manages the opening/closing of doors and passenger flow.
     */
    public void handleDoorsAtCurrentFloor() {
        // Implementation for door handling
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

    /**
     * Checks if any passengers inside want to go up.
     * @return true if at least one passenger wants to go up, false otherwise
     */
    public boolean insideWantsToGoUp() {
        return currentUsers.insideWantsToGoUp();
    }

    /**
     * Checks if all passengers inside want to go down.
     * @return true if all passengers want to go down, false otherwise
     */
    public boolean insideWantsToGoDown() {
        return currentUsers.insideWantsToGoDown();
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
     * Checks if the elevator is going up.
     * @return true if going up, false otherwise
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Sets the elevator's upward direction.
     * @param up true to set direction to up, false otherwise
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    /**
     * Checks if the elevator is going down.
     * @return true if going down, false otherwise
     */
    public boolean isDown() {
        return down;
    }

    /**
     * Sets the elevator's downward direction.
     * @param down true to set direction to down, false otherwise
     */
    public void setDown(boolean down) {
        this.down = down;
    }

    /**
     * Checks if the elevator is currently moving.
     * @return true if moving, false if stationary
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * Sets the elevator's moving state.
     * @param moving true to set as moving, false to set as stationary
     */
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * Gets the queue of current passengers in the elevator.
     * @return The Queue object containing current passengers
     */
    public Queue getCurrentUsers() {
        return currentUsers;
    }

    /**
     * Sets the queue of current passengers in the elevator.
     * @param currentUsers The new Queue of passengers
     */
    public void setCurrentUsers(Queue currentUsers) {
        this.currentUsers = currentUsers;
    }
}
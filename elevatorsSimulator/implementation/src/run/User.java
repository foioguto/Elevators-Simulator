/**
 * This package contains data structures for managing elevator simulations.
 */
package run;


public class User {
    private int currentFloor;
    private int nextFloor;
    private boolean up;

    /**
     * Creates a new User with the specified floor information and direction.
     * @param currentFloor The floor where the user is currently located
     * @param nextFloor The floor where the user wants to go
     * @param up true if the user wants to go up, false if going down
     */
    public User(int currentFloor, int nextFloor, boolean up) {
        this.currentFloor = currentFloor;
        this.nextFloor = nextFloor;
        this.up = up;
    }

    /**
     * Gets the user's current floor.
     * @return The current floor number
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Sets the user's current floor.
     * @param currentFloor The new current floor number
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Gets the user's destination floor.
     * @return The destination floor number
     */
    public int getNextFloor() {
        return nextFloor;
    }

    /**
     * Sets the user's destination floor.
     * @param nextFloor The new destination floor number
     */
    public void setNextFloor(int nextFloor) {
        this.nextFloor = nextFloor;
    }

    /**
     * Checks if the user wants to go up.
     * @return true if the user wants to go up, false if going down
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Sets the user's direction.
     * @param up true if the user wants to go up, false if going down
     */
    public void setUp(boolean up) {
        this.up = up;
    }
}
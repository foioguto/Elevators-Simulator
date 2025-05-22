package run.model;

/**
 * Represents a user (passenger) in the elevator simulation system
 */
public class User {
    // Class fields
    private int currentFloor;
    private int nextFloor;
    private boolean up;
    private boolean priority;

    // ==================== Constructor ====================

    /**
     * Creates a new user with specified floor positions and direction
     */
    public User(int currentFloor, int nextFloor, boolean up, boolean priority) {
        this.currentFloor = currentFloor;
        this.nextFloor = nextFloor;
        this.up = up;
        this.priority = priority;
    }

    // ==================== Floor Position Accessors ====================

    /**
     * Gets the user's current floor position
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     * Updates the user's current floor position
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * Gets the user's destination floor
     */
    public int getNextFloor() {
        return nextFloor;
    }

    /**
     * Updates the user's destination floor
     */
    public void setNextFloor(int nextFloor) {
        this.nextFloor = nextFloor;
    }

    // ==================== Direction Accessors ====================

    /**
     * Checks if user is going upward
     */
    public boolean isUp() {
        return up;
    }

    /**
     * Sets the user's travel direction
     */
    public void setUp(boolean up) {
        this.up = up;
    }

    // ==================== Priority Accessors ====================

    /**
     * Checks if user has priority status
     */
    public boolean isPriority() {
        return priority;
    }

    /**
     * Sets the user's priority status
     */
    public void setPriority(boolean priority) {
        this.priority = priority;
    }
}
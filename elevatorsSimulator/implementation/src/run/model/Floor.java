package run.model;

import run.panel.ExternalPanel;

public class Floor {
    // Class fields
    private final int floor;
    private UserQueue users;
    private final ExternalPanel extPanel;

    // ==================== Constructor ====================

    /**
     * Creates a new floor with given number
     * Initializes empty user queue and external panel
     */
    public Floor(int floor) {
        this.floor = floor;
        this.users = new UserQueue();
        this.extPanel = new ExternalPanel();
    }

    // ==================== Setters ====================

    /**
     * Replaces the current user queue with new set of users
     */
    public void setUsers(UserQueue users) {
        this.users = users;
    }

    // ==================== Getters ====================

    /**
     * @return The floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @return Queue of users waiting on this floor
     */
    public UserQueue getUsers() {
        return users;
    }

    /**
     * @return The external panel (call buttons) for this floor
     */
    public ExternalPanel getExtPanel() {
        return extPanel;
    }
}

package run.model;

import run.panel.ExternalPanel;

public class Floor {
    // Class fields
    private final int floor;
    private UserList users;
    private final ExternalPanel extPanel;

    // ==================== Constructor ====================

    /**
     * Creates a new floor with given number
     * Initializes empty user queue and external panel
     */
    public Floor(int floor) {
        this.floor = floor;
        this.users = new UserList();
        this.extPanel = new ExternalPanel();
    }

    // ==================== Setters ====================

    /**
     * Replaces the current user queue with new set of users
     */
    public void setUsers(UserList users) {
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
    public UserList getUsers() {
        return users;
    }

    /**
     * @return The external panel (call buttons) for this floor
     */
    public ExternalPanel getExtPanel() {
        return extPanel;
    }
}

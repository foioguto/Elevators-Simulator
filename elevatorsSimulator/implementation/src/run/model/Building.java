package run.model;

/**
 * Represents a building with multiple floors and an elevator.
 * The building maintains an array of Floor objects and a reference to an Elevator.
 */
public class Building {
    /** The total number of floors in the building */
    private int totalFloors;

    /** An array containing all Floor objects in the building */
    private Floor[] floors;

    /** The elevator associated with this building */
    private Elevator elevator;

    /**
     * Constructs a new Building with the specified number of floors.
     * Initializes the floors array and generates all Floor objects.
     *
     * @param totalFloors The total number of floors in the building (must be positive)
     */
    public Building(int totalFloors) {
        this.totalFloors = totalFloors;
        this.floors = new Floor[totalFloors];
        generateFloors();
    }

    /**
     * Generates Floor objects for each floor in the building.
     * Each floor is initialized with its corresponding index number.
     */
    private void generateFloors() {
        for (int i = 0; i < totalFloors; i++) {
            floors[i] = new Floor(i);
        }
    }

    /**
     * Returns the Floor object at the specified index.
     *
     * @param index The floor number to retrieve (0-based)
     * @return The Floor object at the specified index
     */
    public Floor getFloor(int index) {
        return floors[index];
    }

    /**
     * Gets the elevator associated with this building.
     *
     * @return The Elevator object, or null if not set
     */
    public Elevator getElevator() {
        return elevator;
    }

    /**
     * Sets the elevator for this building.
     *
     * @param elevator The Elevator object to associate with this building
     */
    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    /**
     * Returns the total number of floors in the building.
     *
     * @return The total number of floors
     */
    public int getTotalFloors() {
        return totalFloors;
    }

    /**
     * Returns an array of all Floor objects in the building.
     *
     * @return An array of Floor objects representing all floors
     */
    public Floor[] getFloors() {
        return floors;
    }
}

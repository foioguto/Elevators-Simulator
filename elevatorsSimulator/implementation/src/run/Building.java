package run;

import dataStructure.Floor;

/**
 * Represents a building with multiple floors and an elevator.
 * This class manages the building structure, including its floors and elevator.
 */
public class Building {
    private int totalFloors;
    private Floor[] floors;
    private Elevator elevator;

    /**
     * Constructs a new Building with the specified number of floors.
     * Initializes the floors array based on the total number of floors.
     *
     * @param totalFloors The total number of floors in the building
     */
    public Building(int totalFloors) {
        this.totalFloors = totalFloors;
        this.floors = new Floor[totalFloors];
    }

    /**
     * Sets the elevator instance for the building.
     * @param elevator1 The Elevator object to associate with the building
     */
    public void setElevator(Elevator elevator1) {
        this.elevator = elevator1;
    }

    /**
     * Gets a specific floor by its index.
     *
     * @param i The index of the floor to retrieve (0-based)
     * @return The Floor object at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    public Floor getFloor(int i) {
        return floors[i];
    }

    /**
     * Gets the array of all floors in the building.
     *
     * @return The array of Floor objects representing all floors
     */
    public Floor[] getFloors() {
        return floors;
    }

    /**
     * Gets the elevator instance associated with the building.
     * @return The Elevator object
     */
    public Elevator getElevator() {
        return elevator;
    }

    /**
     * Gets the total number of floors in the building.
     * @return The total number of floors
     */
    public int getTotalFloors() {
        return totalFloors;
    }
}
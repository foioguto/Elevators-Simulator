package run;

import dataStructure.Vector;

/**
 * Represents a building with multiple floors and elevators.
 * This class manages the building structure including its floors and elevators.
 */
public class Building {
    private int totalFloors;
    private Vector[] vectors;
    private Elevator elevator;

    public void setElevator(Elevator elevator1) {
        this.elevator = elevator1;
    }

    public Elevator getElevator() {
        return elevator;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    /**
     * Constructs a new Building with the specified number of floors.
     * Note: This constructor only sets the total floors count. The floors array
     * and elevators need to be initialized separately.
     *
     * @param totalFloors The total number of floors in the building
     */
    public Building(int totalFloors) {
        this.totalFloors = totalFloors;
        this.vectors = new Vector[totalFloors];
    }

    /**
     * Gets the array of all floors in the building.
     *
     * @return The array of Floor objects representing all floors
     */
    public Vector[] getFloors() {
        return vectors;
    }

    /**
     * Gets a specific floor by its index.
     *
     * @param i The index of the floor to retrieve (0-based)
     * @return The Floor object at the specified index
     * @throws ArrayIndexOutOfBoundsException if the index is out of bounds
     */
    public Vector getFloor(int i) {
        return vectors[i];
    }
}
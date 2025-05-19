package run;

import run.dataStructure.Array;

public class Building {
    private final int totalFloors;
    private final Array<Floor> floors;
    private Array<Elevator> elevators;
    private int numElevators;

    public Building(int totalFloors) {
        if (totalFloors < 5) {
            throw new IllegalArgumentException("Building must have at least 5 floor");
        }
        this.totalFloors = totalFloors;
        this.floors = new Array<Floor>(totalFloors);
        generateFloors();
    }

    private void generateFloors() {
        for (int i = 0; i < totalFloors; i++) {
            floors.set(i, new Floor(i));
        }
    }

    public Floor getFloor(int index) {
        if (index < 0 || index >= totalFloors) {
            throw new IllegalArgumentException("Floor index out of bounds");
        }
        return floors.get(index);
    }

    public Elevator getElevator(int index) {
        if (elevators == null || index < 0 || index >= numElevators) {
            throw new IllegalArgumentException("Invalid elevator index");
        }
        return elevators.get(index);
    }

    public Array<Elevator> getElevators() {
        return elevators != null ? elevators : new Array<Elevator>(100);
    }

    public void setElevators(Array<Elevator> elevators) {
        if (elevators == null) {
            throw new IllegalArgumentException("Elevators array cannot be null");
        }
        this.elevators = elevators;
        this.numElevators = elevators.size();
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public Array<Floor> getFloors() {
        return floors;
    }

    public int getNumElevators() {
        return numElevators;
    }
}
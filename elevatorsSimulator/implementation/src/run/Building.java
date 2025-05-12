package run;

import dataStructure.Floor;

public class Building {
    private int totalFloors;
    private Floor[] floors;
    private Elevator elevator;

    public Building(int totalFloors) {
        this.totalFloors = totalFloors;
        this.floors = new Floor[totalFloors];
        generateFloors();
    }

    private void generateFloors() {
        for (int i = 0; i < totalFloors; i++) {
            floors[i] = new Floor(i);
        }
    }

    public Floor getFloor(int index) {
        return floors[index];
    }

    public Elevator getElevator() {
        return elevator;
    }

    public void setElevator(Elevator elevator) {
        this.elevator = elevator;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public Floor[] getFloors() {
        return floors;
    }
}

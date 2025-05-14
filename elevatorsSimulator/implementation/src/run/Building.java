package run;

public class Building {
    private int totalFloors;
    private Floor[] floors;
    private Elevator[] elevators;
    private int numElevators;

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

    public Elevator[] getElevators() {
        return elevators;
    }

    public void setElevators(Elevator[] elevators) {
        this.elevators = elevators;
        this.numElevators = elevators.length;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public int getNumElevators() {
        return numElevators;
    }
}
public class Building {
    private int numberOfFloors;
    private int numberOfElevators;
    private Elevator[] elevators;
    private Floor[] floors;

    public Building(int numberOfFloors, int numberOfElevators) {
        this.numberOfFloors = numberOfFloors;
        this.numberOfElevators = numberOfElevators;
        this.elevators = new Elevator[numberOfElevators];
        this.floors = new Floor[numberOfFloors];
    }

    public void initializeElevators() {
        // for the future me
    }

    public void initializeFloors() {
        // for the future me
    }

    public int getNumberOfFloors() {
        return numberOfFloors;
    }
    public void setNumberOfFloors(int numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public int getNumberOfElevators() {
        return numberOfElevators;
    }

    public void setNumberOfElevators(int numberOfElevators) {
        this.numberOfElevators = numberOfElevators;
    }

    public Elevator[] getElevators() {
        return elevators;
    }

    public void setElevators(Elevator[] elevators) {
        this.elevators = elevators;
    }

    public Floor[] getFloors() {
        return floors;
    }

    public void setFloors(Floor[] floors) {
        this.floors = floors;
    }
}

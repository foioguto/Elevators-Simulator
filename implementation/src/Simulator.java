public class Simulator {

    // python user below
    public Simulator(int numberOfFloors, int numberOfElevators, int heuristics) {
        Building building = new Building(numberOfFloors, numberOfElevators);
        ElevatorController controller = new ElevatorController(building.getElevators(), heuristics);
    }

    public void startSimulation() {
        // me.next problem
    }
}

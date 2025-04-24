public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingRandom();
        simulator.setUsersBuilding();
        simulator.setElevators();
        simulator.simulateElevatorRuns(5);
     }
}

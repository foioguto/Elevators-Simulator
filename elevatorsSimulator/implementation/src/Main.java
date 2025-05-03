public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(6);
        simulator.setUsersBuilding();
        simulator.setElevators();
        simulator.simulateElevatorRuns(1);
     }
}

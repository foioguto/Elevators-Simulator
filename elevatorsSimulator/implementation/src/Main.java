public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(6);
        simulator.setElevators(8, false);
        simulator.simulateElevatorRuns(1);
     }
}

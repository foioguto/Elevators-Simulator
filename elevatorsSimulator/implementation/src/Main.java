public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(5);
        simulator.setElevators(8, 2);
        simulator.simulateElevatorRuns(1);
     }
}

import config.parameters;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(parameters.MAX_FLOORS);
        simulator.setElevators(parameters.MAX_CAPACITY, parameters.MAX_ELEVATORS);
        simulator.simulateElevatorRuns(parameters.END_TIME - parameters.START_TIME);
     }
}

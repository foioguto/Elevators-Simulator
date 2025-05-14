import config.parameters;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(parameters.MAX_FLOORS);
        simulator.setElevators(parameters.MAX_CAPACITY, parameters.MAX_ELEVATORS);
        for (int i = 0; i < parameters.MAX_ELEVATORS; i++) {
            simulator.simulateElevatorRuns(parameters.END_TIME - parameters.START_TIME, i);
        }
     }
}

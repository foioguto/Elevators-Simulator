import config.parameters;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();
        EventsList eventsList = new EventsList();

        simulator.startBuildingManual(parameters.MAX_FLOORS);
        simulator.generateElevators(parameters.MAX_CAPACITY, parameters.MAX_ELEVATORS);
        simulator.startRun(simulator.getBuilding());

        while(simulator.getTimeInHours() < parameters.END_TIME - parameters.START_TIME) {
            if (simulator.getTimeInHours() % 2 == 0) {
                eventsList.setEvents(1);
            }
        }
     }
}

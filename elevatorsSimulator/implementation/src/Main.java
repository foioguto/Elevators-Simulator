import config.parameters;
import simulator.EventsList;
import simulator.events.ElevatorsEvents;

public class Main {
    public static void main(String[] args) {
        EventsList eventsList = new EventsList();
        
        simulator.startBuildingManual(parameters.MAX_FLOORS);
        simulator.generateElevators(parameters.MAX_CAPACITY, parameters.MAX_ELEVATORS);
        simulator.startRun(simulator.getBuilding());

        while(simulator.getTimeInHours() < parameters.END_TIME - parameters.START_TIME) {
            if (simulator.getTimeInHours() % 2 == 0) {
                eventsList.callEvent();
                simulator.increasetTimeInHours();
            }
        }
     }
}

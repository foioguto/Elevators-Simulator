package simulator;

import run.Building;
import run.dataStructure.Array;
import config.Parameters;
import simulator.events.BuildingEvents;
import simulator.events.ElevatorsEvents;
import simulator.events.UserEvents;

/**
 * Manages a list of simulation events and coordinates their execution.
 * This class serves as the central event dispatcher for the elevator simulation.
 */
public class EventsList {
    private Array<String> eventsArray;
    private Building building;

    /**
     * Constructs a new EventsList with default parameters.
     * Initializes the building with maximum floors from Parameters.
     */
    public EventsList() {
        this.eventsArray = new Array<>(100);
        this.building = new Building(Parameters.MAX_FLOORS);
    }
    
    /**
     * Adds an event to the event queue.
     *
     * @param eventName the name of the event to add
     * @throws IllegalArgumentException if eventName is null or empty
     */
    public void setEvent(String eventName) {
        if (eventName == null || eventName.isEmpty()) {
            throw new IllegalArgumentException("Event name cannot be null or empty");
        }
        eventsArray.append(eventName);
    }

    /**
     * Executes the next event in the queue.
     * Processes the first event and removes it from the queue.
     */
    public void callEvent() {
        if (eventsArray.isEmpty()) {
            System.out.println("No events to process");
            return;
        }

        BuildingEvents buildingEvents = new BuildingEvents(building);
        UserEvents userEvents = new UserEvents(building);
        ElevatorsEvents elevatorsEvents = new ElevatorsEvents(building, building.getElevators());

        String event = eventsArray.get(0);

        switch(event) {
            case "setUsersBuilding":
                userEvents.setUsersBuilding();
                break;
            case "exitBuilding":
                if (!building.getFloor(0).getUsers().isEmpty()) {
                    building.getFloor(0).getUsers().removeLast();
                }
                break;
            case "generateNewUserRequests":
                userEvents.generateNewUserRequests();
                break;
            case "printBuildingState":
                buildingEvents.printBuildingState();    
                break;
            case "generateElevators":
                elevatorsEvents.generateElevators(Parameters.MAX_CAPACITY);
                break;    
            case "startRun":
                elevatorsEvents.startRun();
                break;    
            case "stopAllElevators":
                elevatorsEvents.stopAllElevators();
                break;
            default:
                System.out.println("Invalid Event Name: " + event);
                break;
        }

        eventsArray.remove(0);
    }

    /**
     * Executes the standard sequence of time-based events for the simulation.
     * Manages the main simulation loop and periodic events.
     */
    public void callTimeEvents() {
        ElevatorsEvents elevatorsEvents = new ElevatorsEvents(building, building.getElevators());
  
        this.setEvent("setUsersBuilding");
        this.callEvent();

        this.setEvent("printBuildingState");
        this.callEvent();

        this.setEvent("generateElevators");
        this.callEvent();

        this.setEvent("startRun");
        this.callEvent();

        while(elevatorsEvents.getTimeInHours() < elevatorsEvents.getTimes()) {
            if (elevatorsEvents.getTimeInHours() % 2 == 0) {
                this.setEvent("setUsersBuilding");
                this.callEvent();
            }
            
            this.setEvent("generateNewUserRequests");
            this.callEvent();
            
            elevatorsEvents.increaseTimeInHours();
        }
        
        this.setEvent("stopAllElevators");
        this.callEvent();
    }

}

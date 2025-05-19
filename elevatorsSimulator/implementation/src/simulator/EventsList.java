package simulator;

import run.Building;
import config.Parameters;
import run.Array;
import simulator.events.BuildingEvents;
import simulator.events.ElevatorsEvents;
import simulator.events.UserEvents;

public class EventsList {
    private Array<String> eventsArray;
    private Building building = new Building(Parameters.MAX_FLOORS);


    public EventsList() {
        eventsArray = new Array<>(100);
    }
    
    public void setEvent(String eventName) {
        eventsArray.append(eventName);
    }

    public void callEvent() {
        BuildingEvents buildingEvents = new BuildingEvents(building);
        UserEvents userEvents = new UserEvents(building);
        ElevatorsEvents elevatorsEvents = new ElevatorsEvents(building, building.getElevators());

        String event = eventsArray.getElement(0);

        switch(event) {
            case "setUsersBuilding":
                userEvents.setUsersBuilding();
                break;
            case "exitBuilding":
                building.getFloor(0).getUsers().removeLast();
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
            System.out.println("Invalid Event Name!");
            break;
        }

        eventsArray.remove(0);
    }

    public void callTimeEvents() {
        ElevatorsEvents elevatorsEvents = new ElevatorsEvents(building, building.getElevators());

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
        }
    }

}

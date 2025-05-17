package simulator;

import run.Building;
import run.Array;
import simulator.events.BuildingEvents;
import simulator.events.UserEvents;

public class EventsList {
    private Array<String> eventsArray;
    private String event;
    private Building building;

    public EventsList(Building building) {
        this.building = building;
        eventsArray = new Array<>(100);
    }
    
    public void setEvent(String eventName, int priority) {
        eventsArray.append(eventName);
    }

    public void callEvent() {
        BuildingEvents buildingEvents = new BuildingEvents(building);
        UserEvents userEvents = new UserEvents(building);
        setEvent(eventsArray.getElement(0));
    
        switch(getEvent()){
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
            default:
            System.out.println("Invalid Event Name!");
            break;
        }
        
        eventsArray.remove(0);
    }

    public String getEvent(){
        return this.event;
    }

    public void setEvent(String eventName){
        this.event = eventName;
    }


}

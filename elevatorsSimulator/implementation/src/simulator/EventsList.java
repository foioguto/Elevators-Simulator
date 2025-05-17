package simulator;

import run.UserQueue;
import run.Building;
import simulator.events.BuildingEvents;
import simulator.events.UserEvents;

public class EventsList {
    UserQueue eventList = new UserQueue();
    private int event;
    private Building building;

    public EventsList(Building building) {
        this.building = building;
    }
    
    public void setEvent(int eventNumber, int priority) {
        
    }

    public void callEvents(int event) {
        BuildingEvents buildingEvents = new BuildingEvents(building);
        UserEvents userEvents = new UserEvents(building);
        setEvent(event);
    
        switch(getEvent()){
            case 1:
                userEvents.setUsersBuilding();
                break;
            case 2:
                building.getFloor(0).getUsers().removeLast();
                break;
            case 3:
                userEvents.generateNewUserRequests();
                break;
            default:
            System.out.println("Error!!!");
            break;
        }

    }

    public int getEvent(){
        return this.event;
    }

    public void setEvent(int eventNumber){
        this.event = eventNumber;
    }


}

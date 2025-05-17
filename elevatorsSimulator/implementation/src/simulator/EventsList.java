package simulator;

import run.Building;
import config.parameters;
import run.Array;
import simulator.events.BuildingEvents;
import simulator.events.ElevatorsEvents;
import simulator.events.UserEvents;

public class EventsList {
    private Array<String> eventsArray;
    private String event;
    private Building building;

    public EventsList() {
        eventsArray = new Array<>(100);
    }
    
    public void setEvent(String eventName, int priority) {
        eventsArray.append(eventName);
    }

    public void callEvent() {
        BuildingEvents buildingEvents = new BuildingEvents(building);
        UserEvents userEvents = new UserEvents(building);
        ElevatorsEvents elevatorsEvents = new ElevatorsEvents(building, building.getElevators());

        setEvent(eventsArray.getElement(0));
    
        switch(getEvent()) {
            case "startBuildingManual":
                buildingEvents.startBuildingManual(parameters.MAX_FLOORS);
                break;
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
            case "startElevator":
            for (int i = 0; i < parameters.MAX_ELEVATORS; i++) {    
                elevatorsEvents.startElevator(building.getElevator(i));;
            }    
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
        BuildingEvents buildingEvents = new BuildingEvents(building);
        UserEvents userEvents = new UserEvents(building);
        ElevatorsEvents elevatorsEvents = new ElevatorsEvents(building, building.getElevators());


        while(elevatorsEvents.getTimeInHours() < parameters.END_TIME - parameters.START_TIME) {
            if (elevatorsEvents.getTimeInHours() % 2 == 0) {
                this.setEvent("setUsersBuilding");
                this.callEvent();
            }
        }
    }

    public String getEvent(){
        return this.event;
    }

    public void setEvent(String eventName){
        this.event = eventName;
    }


}

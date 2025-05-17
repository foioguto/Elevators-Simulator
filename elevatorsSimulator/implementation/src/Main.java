import simulator.EventsList;

public class Main {

    public static void main(String[] args) {
        EventsList eventsList = new EventsList();
        
        eventsList.setEvent("startBuildingManual");
        eventsList.callEvent();

        eventsList.setEvent("startElevator");
        eventsList.callEvent();

        eventsList.setEvent("startRun");
        eventsList.callEvent();
     }

}

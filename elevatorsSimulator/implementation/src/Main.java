import simulator.EventsList;

public class Main {
    public static void main(String[] args) {
        EventsList eventsList = new EventsList();
        
        eventsList.setEvent("startBuildingManual");
        eventsList.callEvent();
        eventsList.setEvent("generateElevators");
        eventsList.callEvent();
        eventsList.setEvent("startRun");
        eventsList.callEvent();
     }

}

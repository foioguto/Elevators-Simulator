
import run.UserQueue;

public class EventsList {
    UserQueue EventList = new UserQueue();
    private int event;
    
    public void setEvents(int event) {
        Simulator simulator = new Simulator();
    
        switch(getEvent()){
            case 1:
                simulator.setUsersBuilding();
                break;
            case 2:
                simulator.getBuilding().getFloor(0).getUsers().removeLast();
                break;
            case 3:
                simulator.getBuilding().getElevator(0).setCurrentUsers(null);
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

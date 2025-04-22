import dataStructure.Vector;
import run.Building;
import run.Elevator;

import java.util.Random;

public class Simulator {
    Vector vectors[];
    Random random = new Random();
    Building building;
    Elevator elevator;

    public void startBuildingRandom(){
        int min = 2;
        int floorsNumber = 2 + random.nextInt(8);
        this.building = new Building(floorsNumber);
    }

    public void startBuildingManual(int floorsNumber){
        this.building = new Building(floorsNumber);
    }

    public void setUsersBuilding(){
        for(int i=0; i<building.getFloors().length; i++){
            building.getFloors()[i] = new Vector(i);
            building.getFloors()[i].setUsers(random.nextInt(5), building.getTotalFloors(), building.getFloors()[i].getFloor());
        }
    }

    public void setElevators(){
        elevator = new Elevator(8);
        building.setElevator(elevator);
    }

}

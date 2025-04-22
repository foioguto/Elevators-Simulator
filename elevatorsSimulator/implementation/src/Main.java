import dataStructure.List;
import run.User;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(4);
        simulator.setUsersBuilding();
        simulator.setElevators();
        simulator.startElevator();
     }
}

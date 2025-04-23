import dataStructure.List;
import run.User;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator();

        simulator.startBuildingManual(5);
        simulator.setUsersBuilding();
        simulator.setElevators();
        simulator.startElevator();
     }
}

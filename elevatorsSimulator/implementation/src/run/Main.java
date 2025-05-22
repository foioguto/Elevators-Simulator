package run;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Simulator simulator = new Simulator();

        System.out.println("=== ELEVATOR PARAMETERS ===");

        System.out.print("Number of floors in the building: ");
        int numFloors = scanner.nextInt();

        System.out.print("Maximum capacity of the elevator: ");
        int numElevators = scanner.nextInt();

        System.out.print("Simulation time: ");
        int hours = scanner.nextInt();

        System.out.print("Number of simulation runs: ");
        int numRuns = scanner.nextInt();

        simulator.startBuildingManual(numFloors);
        simulator.setElevators(numElevators);

        if ( (hours < 14 && hours > 10) || (hours < 20 && hours > 16)){
            simulator.simulateElevatorRunsHot(numRuns);
        }else{
            simulator.simulateElevatorRuns(numRuns);
        }

        scanner.close();
    }
}
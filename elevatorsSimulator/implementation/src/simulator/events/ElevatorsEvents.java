package simulator.events;

import run.Building;
import run.Elevator;
import run.Array;
import config.parameters;

public class ElevatorsEvents {
    private Array<Thread> elevatorThreads;
    private Elevator[] elevators;
    private Building building;
    private int timeInHours = 0;

    public ElevatorsEvents(Building building, Elevator[] elevators) {
        elevatorThreads = new Array<>(100);
        this.building = building;
        this.elevators = elevators;
    }

    public void generateElevators(int maxCapacity, int MAX_ELEVATORS) {
        this.elevators = new Elevator[MAX_ELEVATORS];
        for (int i = 0; i < MAX_ELEVATORS; i++) {
            this.elevators[i] = new Elevator(maxCapacity, i);
        }
        building.setElevators(elevators);
    }

    public void startElevator(Elevator elevator) {
        elevator.setBuilding(building);
        Thread thread = new Thread(elevator);
        elevatorThreads.append(thread);
        thread.start();
    }

    public void simulateElevatorRuns(int times, Elevator elevator) {
        UserEvents userEvents = new UserEvents(building);
        BuildingEvents buildingEvents = new BuildingEvents(building);

        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        int i = timeInHours + parameters.START_TIME;
        startElevator(elevator);

        while (i < times) {
            System.out.println("CYCLE #" + timeInHours);

            userEvents.setUsersBuilding();
            buildingEvents.printBuildingState();
            
            if (elevators[0].getTotalTime() >= 60) { //just the first elevator counts the time 
                timeInHours++;
                i++;
                elevators[0].resetTotalTime();

                System.out.println("END OF CYCLE #" + timeInHours + "\n");
            }

            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        generateLogs(userEvents.getTotalPeople());
        System.out.println("Simulation completed.");
    }

     public void startRun() {
        for (int i = 0; i < building.getNumElevators(); i++) {
            simulateElevatorRuns(parameters.END_TIME - parameters.START_TIME, building.getElevator(i));
        }
    }

    public void stopAllElevators() {
        for (Elevator elevator : elevators) {
            elevator.stopElevatorRun(); 
        }
    }

    public void generateLogs(int totalPeople) {
        int total = 0;
        for (int i = 0; i < building.getNumElevators(); i++) {
            System.out.println("Elevator " + elevators[i].getElevatorNumber() + " Energy spent:" + elevators[i].getTotalEnergy());
            total += elevators[i].getTotalEnergy();
        }
        System.out.println("Total users transported: " + totalPeople);
        System.out.println("Total energy spent: " + total);
        System.out.println("Total cost: " + (total * parameters.COST_PER_KWH));
    }

    public int getTimeInHours() {
        return timeInHours;
    }

    public void increaseTimeInHours() {
        timeInHours++;
    }

} 

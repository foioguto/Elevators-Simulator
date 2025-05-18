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
    private int times;

    public ElevatorsEvents(Building building, Elevator[] elevators) {
        elevatorThreads = new Array<>(100);
        this.building = building;
        this.elevators = elevators;
        this.times = parameters.END_TIME - parameters.START_TIME;
    }

    public void generateElevators(int maxCapacity) {
        if (building == null) {
            System.err.println("Error: Building not initialized.");
            return;
        }

        int numElevators = parameters.MAX_ELEVATORS;
        this.elevators = new Elevator[numElevators];

        for (int i = 0; i < numElevators; i++) {
            elevators[i] = new Elevator(maxCapacity, i);
            elevators[i].setBuilding(building); 
        }

        building.setElevators(elevators);
        System.out.println("Elevators created: " + numElevators);
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
            System.out.println("Time: " + i + "\n");

            userEvents.setUsersBuilding();
            buildingEvents.printBuildingState();
            
            if (elevators[0].getTotalTime() >= 60) { //just the first elevator counts the time 
                timeInHours++;
                i++;
                elevators[0].resetTotalTime();

                System.out.println("Time: " + i + "\n");
            }

            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

     public void startRun() {
        for (int i = 0; i < building.getNumElevators(); i++) {
            Elevator elevator = building.getElevator(i);
            if (elevator != null) {
                simulateElevatorRuns(this.getTimes(), building.getElevator(i));
            }    
        }
    }

    public void stopAllElevators() {
        UserEvents userEvents = new UserEvents(building);

        for (Elevator elevator : elevators) {
            elevator.stopElevatorRun(); 
        }
        
        generateLogs(userEvents.getTotalPeople());
        System.out.println("Simulation completed.");
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

    public int getTimes() {
        return this.times;
    }

} 

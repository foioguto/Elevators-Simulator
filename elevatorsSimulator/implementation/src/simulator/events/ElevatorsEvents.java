package simulator.events;

import run.Building;
import run.Elevator;
import run.dataStructure.Array;
import config.Parameters;

/**
 * Manages elevator simulation events including creation, operation, and logging.
 * This class coordinates elevator threads, user events, and simulation timing.
 */
public class ElevatorsEvents {
    private Array<Thread> elevatorThreads;
    private Array<Elevator> elevators;
    private Building building;
    private int timeInHours = 0;
    private final int times;

    /**
     * Constructs an ElevatorsEvents instance with the specified building and elevators.
     *
     * @param building the building where elevators operate
     * @param elevators initial array of elevators (can be empty)
     * @throws IllegalArgumentException if building is null
     */
    public ElevatorsEvents(Building building, Array<Elevator> elevators) {
        if (building == null) {
            throw new IllegalArgumentException("Building cannot be null");
        }
        this.elevatorThreads = new Array<>(100);
        this.building = building;
        this.elevators = new Array<>(Parameters.MAX_ELEVATORS);
        
        if (elevators != null) {
            for (int i = 0; i < elevators.size(); i++) {
                Elevator e = elevators.get(i);
                if (e != null) {
                    this.elevators.append(e);
                }
            }    
        }
        
        this.times = Parameters.END_TIME - Parameters.START_TIME;
    }

    /**
     * Generates and initializes elevators for the building.
     *
     * @param maxCapacity maximum passenger capacity for each elevator
     * @throws IllegalStateException if building is not initialized
     */
    public void generateElevators(int maxCapacity) {
        if (building == null) {
            throw new IllegalStateException("Building not initialized");
        }
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Elevator capacity must be positive");
        }

        this.elevators = new Array<>(Parameters.MAX_ELEVATORS);

        for (int i = 0; i < Parameters.MAX_ELEVATORS; i++) {

            Elevator elevator = new Elevator(maxCapacity, i);
            elevator.setBuilding(building);
            
            this.elevators.set(i, elevator);
        }

        building.setElevators(elevators);
        System.out.println(Parameters.MAX_ELEVATORS + " elevators created with capacity " + maxCapacity);
    }

    /**
     * Starts a single elevator in its own thread.
     *
     * @param elevator the elevator to start
     * @throws IllegalArgumentException if elevator is null
     */
    public void startElevator(Elevator elevator) {
        if (elevator == null) {
            throw new IllegalArgumentException("Elevator cannot be null");
        }
        
        elevator.setBuilding(building);
        Thread thread = new Thread(elevator);
        elevatorThreads.append(thread);
        thread.start();
    }

    /**
     * Simulates elevator operation for a specified number of cycles.
     *
     * @param times number of simulation cycles to run
     * @param elevator the elevator to simulate
     */
    public void simulateElevatorRuns(int times, Elevator elevator) {
        UserEvents userEvents = new UserEvents(building);
    
        System.out.println("Starting simulation with " + times + " elevator cycles...\n");

        int i = timeInHours + Parameters.START_TIME;
        startElevator(elevator);

        while (i < times) {
            System.out.println("Time: " + i + "\n");
            userEvents.setUsersBuilding();
            
            if (elevators.get(0).getTotalTime() >= 60) {
                timeInHours++;
                i++;
                elevators.get(0).resetTotalTime();
                System.out.println("Time: " + i + "\n");
            }
        }
    }

    /**
     * Starts all elevators in the building for simulation.
     */
    public void startRun() {
        for (int i = 0; i < elevators.size(); i++) {
            Elevator elevator = elevators.get(i);
            if (elevator != null) {
                simulateElevatorRuns(this.getTimes(), elevator);
            }    
        }
    }

    /**
     * Stops all elevator threads and generates simulation logs.
     */
    public void stopAllElevators() {
        UserEvents userEvents = new UserEvents(building);

        for (int i = 0; i < elevators.size(); i++) {
            Elevator elevator = elevators.get(i);
            if (elevator != null) {
                elevator.stopElevatorRun(); 
            }
        }
        
        generateLogs(userEvents.getTotalPeople());
        System.out.println("Simulation completed.");
    }

    /**
     * Generates and prints simulation logs including energy usage and costs.
     *
     * @param totalPeople total number of people transported during simulation
     */
    public void generateLogs(int totalPeople) {
        int totalEnergy = 0;
        for (int i = 0; i < elevators.size(); i++) {
            Elevator elevator = elevators.get(i);
            if (elevator != null) {
                System.out.println("Elevator " + elevator.getElevatorNumber() + 
                                 " Energy spent:" + elevator.getTotalEnergy());
                totalEnergy += elevator.getTotalEnergy();
            }
        }
        System.out.println("Total users transported: " + totalPeople);
        System.out.println("Total energy spent: " + totalEnergy);
        System.out.println("Total cost: " + (totalEnergy * Parameters.COST_PER_KWH));
    }

    /**
     * Gets the current simulation time in hours.
     *
     * @return current time in hours
     */
    public int getTimeInHours() {
        return timeInHours;
    }

    /**
     * Increments the simulation time by one hour.
     */
    public void increaseTimeInHours() {
        timeInHours++;
    }

    /**
     * Gets the total number of simulation cycles.
     *
     * @return total simulation cycles
     */
    public int getTimes() {
        return this.times;
    }
}
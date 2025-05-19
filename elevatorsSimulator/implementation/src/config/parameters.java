package config;

public class Parameters {
    public static final int MAX_FLOORS = 12; // Maximum number of floors

    public static final int MAX_ELEVATORS = 3; // Maximum number of elevators

    public static final int MAX_CAPACITY = 8; // Maximum capacity of each elevator

    public static final int PRIORITY_RARITY = 4; // 1 in 4 users will have priority 1

    public static final int ENERGY_CONSUMPTION = 1; // Energy consumed for handle doors and move between floors

    public static final double TIME = 10; // Time taken per operation (in minutes)

    public static final int START_TIME = 8; // Start time of the simulation (in hours)

    public static final int END_TIME = 21; // End time of the simulation (in hours)

    public static final double COST_PER_KWH = 0.3; // Cost per kWh (in California, USA, it's around $0.30 per kWh)

    public static final int DELAY = 1000; // Milliseconds

    public static final String HEURISTIC = "economy"; // "economy" or "speed" 

    public static final int QUEUE_TIME = 30; // in seconds(must be even number), priority users have half of this time

}

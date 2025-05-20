package config;

import config.heuristic.*;
import run.Elevator;
import run.Building;


public class ElevatorController {
    private final MovementStyle heuristic;

    public ElevatorController(MovementStyle heuristic) {
        this.heuristic = heuristic;
    }

    public int decideNextFloor(Elevator elevator, Building building) {
        return heuristic.decideNextFloor(elevator, building);
    }

    public static ElevatorController chooseHeuristic() {
        MovementStyle heuristic;

        if ("economy".equals(Parameters.HEURISTIC)) {
            heuristic = new EconomyHeuristic();
        } else if ("speed".equals(Parameters.HEURISTIC)) {
            heuristic = new SpeedHeuristic();
        } else {
            throw new IllegalArgumentException("Invalid heuristic input: " + Parameters.HEURISTIC);
        }

        return new ElevatorController(heuristic);
    }

}

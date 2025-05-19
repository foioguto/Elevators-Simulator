package config.heuristic;

import run.Elevator;
import run.Building;

public interface MovementStyle {
    int decideNextFloor(Elevator elevator, Building building);

}

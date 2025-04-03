public class ElevatorController {
    private Elevator[] elevators;
    private int heuristics;

    public ElevatorController(Elevator[] elevators, int heuristics) {
        this.elevators = elevators;
        this.heuristics = heuristics;
    }

    public void handleRequests() {
        // Make the L to handle the request
    }

    public void setHeuristics(int heuristics) {
        this.heuristics = heuristics;
    }

    public Elevator[] getElevators() {
        return elevators;
    }

    public void setElevators(Elevator[] elevators) {
        this.elevators = elevators;
    }

    public int getHeuristics() {
        return heuristics;
    }
}

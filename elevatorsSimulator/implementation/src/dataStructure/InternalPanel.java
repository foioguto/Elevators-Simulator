package dataStructure;

/**
 * InternalPanel is responsible for handling internal elevator requests
 * made by passengers already inside the elevator.
 */
public class InternalPanel {

    public InternalPanel() {
        // No specific initialization required
    }

    /**
     * Checks if any passenger inside the elevator wants to exit at the current floor.
     *
     * @param currentUsers List of users currently in the elevator.
     * @param actualFloor The current floor of the elevator.
     * @return true if at least one user wants to exit here.
     */
    public boolean wantsToExitHere(DoubleLinkedList currentUsers, int actualFloor) {
        DoubleLinkedList.Node current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() == actualFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Checks if any passenger inside the elevator wants to go to a higher floor.
     *
     * @param currentUsers List of users currently in the elevator.
     * @param currentFloor The current floor of the elevator.
     * @return true if at least one user wants to go up.
     */
    public boolean insideWantsToGoUp(DoubleLinkedList currentUsers, int currentFloor) {
        DoubleLinkedList.Node current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() > currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Checks if any passenger inside the elevator wants to go to a lower floor.
     *
     * @param currentUsers List of users currently in the elevator.
     * @param currentFloor The current floor of the elevator.
     * @return true if at least one user wants to go down.
     */
    public boolean insideWantsToGoDown(DoubleLinkedList currentUsers, int currentFloor) {
        DoubleLinkedList.Node current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() < currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }
}

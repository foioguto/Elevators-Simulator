package dataStructure;

public class InternalPanel extends List {

    public InternalPanel() {
    }

    /**
     * Checks if any user in the queue wants to exit at the specified floor.
     * @param actualFloor The floor to check
     * @return true if at least one user wants to exit at this floor
     */
    public boolean wantsToExitHere(List currentUsers, int actualFloor) {
        List.Node current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() == actualFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    public boolean insideWantsToGoUp(List currentUsers, int currentFloor) {
        List.Node current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() > currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }


    /**
     * Checks if all users in the queue want to go down.
     * @return true if all users want to go down
     */
    public boolean insideWantsToGoDown(List currentUsers, int currentFloor) {
        List.Node current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() < currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

}

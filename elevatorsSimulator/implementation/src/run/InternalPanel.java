package run;

/**
 * InternalPanel is responsible for handling internal elevator requests
 * made by passengers already inside the elevator.
 */
public class InternalPanel {

    public InternalPanel() {
        
    }

    /**
     * Checks if any passenger inside the elevator wants to exit at the current floor.
     *
     * @param currentUsers List of users currently in the elevator.
     * @param actualFloor The current floor of the elevator.
     * @return true if at least one user wants to exit here.
     */
    public boolean wantsToExitHere(UserQueue currentUsers, int actualFloor) {
        UserQueue.UserNode current = currentUsers.getHead();

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
    public boolean insideWantsToGoUp(UserQueue currentUsers, int currentFloor) {
        UserQueue.UserNode current = currentUsers.getHead();

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
    public boolean insideWantsToGoDown(UserQueue currentUsers, int currentFloor) {
        UserQueue.UserNode current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() < currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    /**
     * Removes users who requested to exit at the current floor.
     *
     * @param currentUsers List of users currently in the elevator.
     * @param currentFloor The current floor of the elevator.
     */
    public void exitElevator(UserQueue currentUsers, int currentFloor) {
        UserQueue.UserNode current = currentUsers.getHead();
        UserQueue.UserNode prev = null;

        while (current != null) {
            if (current.user.getNextFloor() == currentFloor) {
                if (prev == null) {
                    currentUsers.setHead(current.next);
                } else {
                    prev.next = current.next;
                }

                if (current == currentUsers.getTail()) {
                    currentUsers.setTail(prev);
                }

                currentUsers.decrementSize();
                current = (prev == null) ? currentUsers.getHead() : prev.next;
                current.user.setWaiting(false);
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

}

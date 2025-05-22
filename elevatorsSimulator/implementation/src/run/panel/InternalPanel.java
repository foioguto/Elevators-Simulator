package run.panel;
import run.model.UserQueue;

public class InternalPanel {

    // ==================== Constructor ====================
    public InternalPanel() {
        // No specific initialization required
    }

    // ==================== Exit Request Methods ====================
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

    public void detectExitRequests(UserQueue currentUsers, int currentFloor) {
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
            } else {
                prev = current;
                current = current.next;
            }
        }
    }

    // ==================== Direction Request Methods ====================
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
}
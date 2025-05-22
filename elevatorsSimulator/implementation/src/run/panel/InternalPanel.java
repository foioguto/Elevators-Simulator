package run.panel;
import run.model.UserList;

public class InternalPanel {

    // ==================== Constructor ====================
    public InternalPanel() {
        // No specific initialization required
    }

    // ==================== Exit Request Methods ====================
    public boolean wantsToExitHere(UserList currentUsers, int actualFloor) {
        UserList.UserNode current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() == actualFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public void detectExitRequests(UserList currentUsers, int currentFloor) {
        UserList.UserNode current = currentUsers.getHead();
        UserList.UserNode prev = null;

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
    public boolean insideWantsToGoUp(UserList currentUsers, int currentFloor) {
        UserList.UserNode current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() > currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public boolean insideWantsToGoDown(UserList currentUsers, int currentFloor) {
        UserList.UserNode current = currentUsers.getHead();

        while (current != null) {
            if (current.user.getNextFloor() < currentFloor) {
                return true;
            }
            current = current.next;
        }

        return false;
    }
}
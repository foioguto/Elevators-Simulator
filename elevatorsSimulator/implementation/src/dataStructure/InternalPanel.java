package dataStructure;

public class InternalPanel extends List {
    /**
     * Checks if any user in the queue wants to exit at the specified floor.
     * @param actualFloor The floor to check
     * @return true if at least one user wants to exit at this floor
     */
    public boolean wantsToExitHere(int actualFloor) {
        List.Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.getNextFloor() == actualFloor) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public boolean insideWantsToGoUp() {
        List.Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.isUp()) {
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
    public boolean insideWantsToGoDown() {
        List.Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.isUp()) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

}

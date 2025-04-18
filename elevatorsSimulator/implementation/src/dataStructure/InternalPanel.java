package dataStructure;

public class InternalPanel extends Queue{
    /**
     * Checks if any user in the queue wants to exit at the specified floor.
     * @param actualFloor The floor to check
     * @return true if at least one user wants to exit at this floor
     */
    public boolean wantsToExitHere(int actualFloor) {
        Queue.Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.getNextFloor() == actualFloor) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public boolean insideWantsToGoUp() {
        Queue.Node current = head;
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
        Queue.Node current = head;
        for (int i = 0; i < size; i++) {
            if(current.user.isUp()) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

//
//    /**
//     * Checks if any passengers inside want to go up.
//     *
//     * @return true if at least one passenger wants to go up, false otherwise
//     */
//    public boolean insideWantsToGoUp() {
//        return currentUsers.insideWantsToGoUp();
//    }
//
//    /**
//     * Checks if all passengers inside want to go down.
//     *
//     * @return true if all passengers want to go down, false otherwise
//     */
//    public boolean insideWantsToGoDown() {
//        return currentUsers.insideWantsToGoDown();
//    }
}

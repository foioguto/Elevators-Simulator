import DataEstructure.User;
import java.util.Random;

public class Floor {
    int floor;
    User[] users;
    Random rand;

    public Floor(int floor) {
        this.floor = floor;
    }

    public void setUsers(int usersQuantity, int totalFloors) {
        for(int i=0; i<usersQuantity; i++){
            boolean up = rand.nextBoolean();
            if(up){
                this.users[i] = new User(this.floor,rand.nextInt(floor,totalFloors),true);
            }else{
                this.users[i] = new User(this.floor,rand.nextInt(floor,totalFloors),true);
            }
        }
    }

    public User[] getUsers() {
        return users;
    }

    public User getUser(int i) {
        return users[i];
    }

    public boolean checkUpJoin(){
        for(int i=0; i<users.length; i++){
            if(this.users[i].isUp()){
                return true;
            }
        }
        return false;
    }
}

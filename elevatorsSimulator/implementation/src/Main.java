import dataStructure.Queue;
import dataStructure.User;

public class Main {
    public static void main(String[] args) {
        Queue kiwi = new Queue();
        User me = new User(1, 2, true);
            System.out.println(kiwi.append(me));
            System.out.println(kiwi.delete(me));

    }
}

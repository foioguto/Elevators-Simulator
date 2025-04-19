import dataStructure.List;
import run.User;

public class Main {
    public static void main(String[] args) {
        List kiwi = new List();
        User me = new User(1, 2, true);
            System.out.println(kiwi.append(me));
            System.out.println(kiwi.delete(me));

    }
}

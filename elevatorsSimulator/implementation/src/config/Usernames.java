package config;

import java.util.Random;

public class Usernames {

    public Usernames() {
        //pass
    }

    public String selectRandom() {
            String[] names = {
            "Harry Potter", 
            "Darth Vader", 
            "Tony Stark", 
            "Naruto Uzumaki", 
            "Batman", 
            "Walter White", 
            "Indiana Jones", 
            "Spider-Man", 
            "Lara Croft", 
            "Goku"
        };

        Random random = new Random();
        int position = random.nextInt(names.length);
        String selected = names[position];

        return selected;
    }
}

package com.example.jokeslib;

import java.util.ArrayList;
import java.util.Random;

public class JokesClass {
    private final String[] jokes = {
            "I told my girlfriend she drew her eyebrows too high. She seemed surprised.",
            "I'm so good at sleeping. I can do it with my eyes closed.",
            "When you look really closely, all mirrors look like eyeballs.",
            "What did the pirate say when he turned 80 years old? Aye matey.",
            "I couldn't figure out why the baseball kept getting larger. Then it hit me.",
            "Why couldn't the bicycle stand up? Because it was two tired!",

    };

    public String getJoke(){
        Random random = new Random();
        int size = jokes.length;
        int index = random.nextInt(size + 1);

        return jokes[index];
    }
}

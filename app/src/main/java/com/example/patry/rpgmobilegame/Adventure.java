package com.example.patry.rpgmobilegame;

public class Adventure {

    private String name;
    private String lvl;
    private String difficulty;

    public Adventure(String name, String lvl, String difficulty) {
        this.name = name;
        this.lvl = lvl;
        this.difficulty = difficulty;
    }

    public String getName() {
        return name;
    }

    public String getLvl() {
        return lvl;
    }

    public String getDifficulty() {
        return difficulty;
    }
}

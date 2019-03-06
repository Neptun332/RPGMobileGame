package com.example.patry.rpgmobilegame;

public class Adventure {

    private String name;
    private String lvl;
    private String difficulty;
    private int imageFile;

    public Adventure(String name, String lvl, String difficulty, int imageFile) {
        this.name = name;
        this.lvl = lvl;
        this.difficulty = difficulty;
        this.imageFile = imageFile;
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

    public int getImageFile() {
        return imageFile;
    }
}

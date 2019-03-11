package com.example.patry.rpgmobilegame;

public class Adventure {

    public static final String KEY_ADVENTURE_NAME = "name";
    public static final String KEY__ADVENTURE_LVL_MIN = "levelMin";
    public static final String KEY__ADVENTURE_LVL_MAX = "levelMax";
    public static final String KEY_ADVENTURE_DIFFICULTY = "difficulty";
    public static final String KEY_IMAGE_FILE_ID = "imageFileID";


    private String name;
    private int lvlMin;
    private int lvlMax;
    private String difficulty;
    private int imageFile;

    public Adventure(String name, String difficulty, int imageFile, int lvlMin, int lvlMax) {
        this.name = name;
        this.lvlMin = lvlMin;
        this.lvlMax = lvlMax;
        this.difficulty = difficulty;
        this.imageFile = imageFile;
    }

    public String getName() {
        return name;
    }

    public int getLvlMin() {
        return lvlMin;
    }

    public int getLvlMax() {
        return lvlMax;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getImageFile() {
        return imageFile;
    }
}

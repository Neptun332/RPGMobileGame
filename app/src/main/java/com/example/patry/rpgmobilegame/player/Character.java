package com.example.patry.rpgmobilegame.player;

public class Character {

    public static final String KEY_NAME = "name";
    public static final String KEY_STR = "strength";
    public static final String KEY_AGL = "agility";
    public static final String KEY_VIT = "vitality";

    public static final int DEFAULT_STR = 10;
    public static final int DEFAULT_AGL = 10;
    public static final int DEFAULT_VIT = 10;


    public Character(String name, int strength, int agility, int vitality) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.vitality = vitality;
    }

    public String name;
    public int strength;
    public int agility;
    public int vitality;

}

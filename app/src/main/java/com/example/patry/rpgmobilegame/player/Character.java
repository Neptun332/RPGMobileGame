package com.example.patry.rpgmobilegame.player;

public class Character {

    public static final String KEY_NAME = "name";
    public static final String KEY_LVL = "level";
    public static final String KEY_EXP = "exp";
    public static final String KEY_STR = "strength";
    public static final String KEY_AGL = "agility";
    public static final String KEY_VIT = "vitality";
    public static final String KEY_INT = "intelligence";
    public static final String KEY_WIS = "wisdom";
    public static final String KEY_GOLD = "gold";

    public static final int DEFAULT_STR = 10;
    public static final int DEFAULT_AGL = 10;
    public static final int DEFAULT_VIT = 10;
    public static final int DEFAULT_INT = 10;
    public static final int DEFAULT_WIS = 10;
    public static final int DEFAUL_GOLD = 100;
    public static final int DEFAULT_LVL = 1;
    public static final int DEFAULT_EXP = 0;

    public Character(String name, int strength, int agility, int vitality, int intelligence, int wisdom) {
        this.name = name;
        this.strength = strength;
        this.agility = agility;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
    }

    public String name;
    public int strength;
    public int agility;
    public int vitality;
    public int intelligence;
    public int wisdom;
}

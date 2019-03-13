package com.example.patry.rpgmobilegame;

class Item {

    private int IID;
    private String name;
    private Rarity rarity;
    private int armor;
    private int hp;

    public Item() {
    }

    public Item(int IID,Rarity rarity, String name, int armor, int hp) {
        this.IID = IID;
        this.rarity = rarity;
        this.name = name;
        this.armor = armor;
        this.hp = hp;
    }


    //private int item_lvl;
    //private String implicite;

    public int getIID() {
        return IID;
    }

    public String getName() {
        return name;
    }

    public int getArmor() {
        return armor;
    }

    public int getHp() {
        return hp;
    }

    public Rarity getRarity() {
        return rarity;
    }
}

package com.example.patry.rpgmobilegame.player;

public class EquipmentItem {
    private int mImageResource;
    private String eqText1;
    private String eqText2;

    public EquipmentItem(int mImageResource, String eqText1, String eqText2) {
        this.mImageResource = mImageResource;
        this.eqText1 = eqText1;
        this.eqText2 = eqText2;
    }

    public String getEqText1() {
        return eqText1;
    }

    public String getEqText2() {
        return eqText2;
    }

    public int getmImageResource() {
        return mImageResource;
    }
}

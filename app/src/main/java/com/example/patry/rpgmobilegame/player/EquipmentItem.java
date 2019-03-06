package com.example.patry.rpgmobilegame.player;

public class EquipmentItem {
    private int itemIcon;
    private String itemName;
    private String itemStats;
    private Boolean isEquiped = false;

    public EquipmentItem(int itemIcon, String itemName, String itemStats, Boolean isEquiped) {
        this.itemIcon = itemIcon;
        this.itemName = itemName;
        this.itemStats = itemStats;
        this.isEquiped = isEquiped;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemStats() {
        return itemStats;
    }

    public int getItemIcon() {
        return itemIcon;
    }

    public Boolean getEquiped() {
        return isEquiped;
    }

    public void setEquiped(Boolean equiped) {
        isEquiped = equiped;
    }
}

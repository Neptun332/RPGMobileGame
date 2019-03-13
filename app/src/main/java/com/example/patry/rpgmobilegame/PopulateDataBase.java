package com.example.patry.rpgmobilegame;

import com.example.patry.rpgmobilegame.Adventure.Adventure;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.patry.rpgmobilegame.Adventure.Adventure.KEY_ADVENTURE_DIFFICULTY;
import static com.example.patry.rpgmobilegame.Adventure.Adventure.KEY_ADVENTURE_NAME;
import static com.example.patry.rpgmobilegame.Adventure.Adventure.KEY_IMAGE_FILE_ID;
import static com.example.patry.rpgmobilegame.Adventure.Adventure.KEY__ADVENTURE_LVL_MAX;
import static com.example.patry.rpgmobilegame.Adventure.Adventure.KEY__ADVENTURE_LVL_MIN;

public class PopulateDataBase {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Adventure> adventureList = new ArrayList();
    private List<Item> helmetsList = new ArrayList();
    private List<Item> chestsList = new ArrayList();
    private List<Item> beltsList = new ArrayList();
    private List<Item> bootsList = new ArrayList();
    private List<Item> glovesList = new ArrayList();
    private List<Item> ringsList = new ArrayList();
    private List<Item> weaponsList = new ArrayList();
    private List<Item> necklacesList = new ArrayList();

    private static int itemIndex;

    private void createAdvetures() {
        Adventure adventureForrest = new Adventure("Forrest", "Easy", R.drawable.forrest2, 1, 10);
        Adventure adventureMine = new Adventure("Quarry", "Medium", R.drawable.quarry, 10, 20);
        Adventure adventureCity = new Adventure("Mine", "Hard", R.drawable.mine, 20, 30);

        adventureList.add(adventureForrest);
        adventureList.add(adventureMine);
        adventureList.add(adventureCity);
    }

    public void populateAdventures() {

        createAdvetures();
        Map<String, Object> adventureData;

        for (int i = 0; i < adventureList.size(); i++) {

            adventureData = new HashMap<>();

            adventureData.put(KEY_ADVENTURE_NAME, adventureList.get(i).getName());
            adventureData.put(KEY__ADVENTURE_LVL_MIN, adventureList.get(i).getLvlMin());
            adventureData.put(KEY__ADVENTURE_LVL_MAX, adventureList.get(i).getLvlMax());
            adventureData.put(KEY_ADVENTURE_DIFFICULTY, adventureList.get(i).getDifficulty());
            adventureData.put(KEY_IMAGE_FILE_ID, adventureList.get(i).getImageFile());

            db
                    .collection("Adventure")
                    .document((i + 1) + " " + adventureList.get(i).getName())
                    .set(adventureData);
        }
    }

    private void createHelmets() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Wooden training helmet",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Wooden training helmet",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Wooden training helmet",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Wooden training helmet",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Wooden training helmet of masters",5, 2);

        helmetsList.add(item1);
        helmetsList.add(item2);
        helmetsList.add(item3);
        helmetsList.add(item4);
        helmetsList.add(item5);
    }

    private void populateHelmets() {
        createHelmets();
        for (int i = 0; i < helmetsList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Helmets")
                    .document( Integer.toString(helmetsList.get(i).getIID()))
                    .set(helmetsList.get(i));

        }
    }

    private void createChests() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Wooden training chestplate",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Wooden training chestplate",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Wooden training chestplate",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Wooden training chestplate",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Wooden training chestplate of masters",5, 2);

        chestsList.add(item1);
        chestsList.add(item2);
        chestsList.add(item3);
        chestsList.add(item4);
        chestsList.add(item5);
    }

    private void populateChests() {
        createChests();
        for (int i = 0; i < chestsList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Chests")
                    .document( Integer.toString(chestsList.get(i).getIID()))
                    .set(chestsList.get(i));

        }
    }

    private void createBelts() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Leather training belt",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Leather training belt",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Leather training belt",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Leather training belt",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Leather training belt of masters",5, 2);

        beltsList.add(item1);
        beltsList.add(item2);
        beltsList.add(item3);
        beltsList.add(item4);
        beltsList.add(item5);
    }

    private void populateBelts() {
        createBelts();
        for (int i = 0; i < beltsList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Belts")
                    .document( Integer.toString(beltsList.get(i).getIID()))
                    .set(beltsList.get(i));

        }
    }

    private void createBoots() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Leather training boots",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Leather training boots",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Leather training boots",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Leather training boots",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Leather training boots of masters",5, 2);

        bootsList.add(item1);
        bootsList.add(item2);
        bootsList.add(item3);
        bootsList.add(item4);
        bootsList.add(item5);
    }

    private void populateBoots() {
        createBoots();
        for (int i = 0; i < bootsList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Boots")
                    .document( Integer.toString(bootsList.get(i).getIID()))
                    .set(bootsList.get(i));

        }
    }

    private void createGloves() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Leather training gloves",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Leather training glowes",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Leather training glowes",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Leather training glowes",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Leather training glowes of masters",5, 2);

        glovesList.add(item1);
        glovesList.add(item2);
        glovesList.add(item3);
        glovesList.add(item4);
        glovesList.add(item5);
    }

    private void populateGloves() {
        createGloves();
        for (int i = 0; i < glovesList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Gloves")
                    .document( Integer.toString(glovesList.get(i).getIID()))
                    .set(glovesList.get(i));

        }
    }

    private void createRings() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Wooden training ring",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Wooden training ring",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Wooden training ring",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Wooden training ring",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Wooden training ring of masters",5, 2);

        ringsList.add(item1);
        ringsList.add(item2);
        ringsList.add(item3);
        ringsList.add(item4);
        ringsList.add(item5);
    }

    private void populateRings() {
        createRings();
        for (int i = 0; i < ringsList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Rings")
                    .document( Integer.toString(ringsList.get(i).getIID()))
                    .set(ringsList.get(i));

        }
    }

    private void createWeapons() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Wooden training sword",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Wooden training sword",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Wooden training sword",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Wooden training sword",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Wooden training sword of masters",5, 2);

        weaponsList.add(item1);
        weaponsList.add(item2);
        weaponsList.add(item3);
        weaponsList.add(item4);
        weaponsList.add(item5);
    }

    private void populateWeapons() {
        createWeapons();
        for (int i = 0; i < weaponsList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Weapons")
                    .document( Integer.toString(weaponsList.get(i).getIID()))
                    .set(weaponsList.get(i));

        }
    }

    private void createNecklaces() {

        Item item1 = new Item(itemIndex++,Rarity.COMMON, "Wooden training necklace",1, 2);
        Item item2 = new Item(itemIndex++,Rarity.MAGIC, "Wooden training necklace",2, 2);
        Item item3 = new Item(itemIndex++,Rarity.RARE, "Wooden training necklace",3, 2);
        Item item4 = new Item(itemIndex++,Rarity.EPIC, "Wooden training necklace",4, 2);
        Item item5 = new Item(itemIndex++,Rarity.UNIQUE, "Wooden training necklace of masters",5, 2);

        necklacesList.add(item1);
        necklacesList.add(item2);
        necklacesList.add(item3);
        necklacesList.add(item4);
        necklacesList.add(item5);
    }

    private void populateNecklace() {
        createNecklaces();
        for (int i = 0; i < necklacesList.size(); i++) {
            db
                    .collection("Items")
                    .document("Equipment")
                    .collection("Necklaces")
                    .document( Integer.toString(necklacesList.get(i).getIID()))
                    .set(necklacesList.get(i));

        }
    }

    public void populateItems(){
        populateHelmets();
        populateChests();
        populateBelts();
        populateBoots();
        populateGloves();
        populateRings();
        populateWeapons();
        populateNecklace();
    }

}

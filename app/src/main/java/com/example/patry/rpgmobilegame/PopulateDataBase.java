package com.example.patry.rpgmobilegame;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.patry.rpgmobilegame.Adventure.KEY_ADVENTURE_DIFFICULTY;
import static com.example.patry.rpgmobilegame.Adventure.KEY_ADVENTURE_NAME;
import static com.example.patry.rpgmobilegame.Adventure.KEY_IMAGE_FILE_ID;
import static com.example.patry.rpgmobilegame.Adventure.KEY__ADVENTURE_LVL_MAX;
import static com.example.patry.rpgmobilegame.Adventure.KEY__ADVENTURE_LVL_MIN;

public class PopulateDataBase {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<Adventure> adventureList = new ArrayList();

    private void createAdvetures() {
        Adventure adventureForrest = new Adventure("Forrest", "Easy", R.drawable.forrest2,1,10);
        Adventure adventureMine = new Adventure("Quarry", "Medium", R.drawable.quarry, 10 ,20 );
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
            adventureData.put(KEY_IMAGE_FILE_ID,adventureList.get(i).getImageFile() );

            db
                    .collection("Adventure")
                    .document(adventureList.get(i).getName())
                    .set(adventureData);
        }
    }

}

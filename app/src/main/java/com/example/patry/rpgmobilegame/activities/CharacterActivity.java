package com.example.patry.rpgmobilegame.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.player.Character;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

import javax.annotation.Nullable;

import static java.lang.Math.toIntExact;

public class CharacterActivity extends AppCompatActivity {
    private static final String TAG = "CharacterActivity";

    private static final String KEY_NAME = "name";
    private static final String KEY_STR = "strength";
    private static final String KEY_AGL = "agility";
    private static final String KEY_VIT = "vitality";

    private TextView characterName;
    private TextView characterStr;
    private TextView characterAgl;
    private TextView characterVit;
    //temp
    private String UID = "mfQUhMQHJs2iNPiPK7rc";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference charRef = db.collection("users").document(UID).collection("Character").document("Stats");

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        characterName =  findViewById(R.id.nameText);
        characterStr = findViewById(R.id.strText);
        characterAgl = findViewById(R.id.aglText);
        characterVit = findViewById(R.id.vitText);
    }
    // Listener for any data changes in DB, if something changes view will be updated
    @Override
    protected void onStart(){
        super.onStart();
        charRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null) {
                    Toast.makeText(CharacterActivity.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,e.toString());
                    return;
                }

                if(documentSnapshot.exists()) {
                    Map<String, Object> charMap = documentSnapshot.getData();
                    character = new Character((String) charMap.get(KEY_NAME),toIntExact((long)charMap.get(KEY_STR)),toIntExact((long)charMap.get(KEY_AGL)),toIntExact((long)charMap.get(KEY_VIT)));
                   // character = new Character((String) charMap.get(KEY_NAME),10,10,10);
                    characterName.setText("Name: " + character.name);
                    characterStr.setText("Strength: " + character.strength);
                    characterAgl.setText("Agility: " + character.agility);
                    characterVit.setText("Vitality: " + character.vitality);

                } else {
                    Toast.makeText(CharacterActivity.this, "document does not exists", Toast.LENGTH_SHORT);
                }
            }
        });
    }
    void loadCharacterData() {
        charRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists()) {
                            character = new Character(documentSnapshot.getString(KEY_NAME),10,10,10);
                            characterName.setText(character.name);
                        } else {
                            Toast.makeText(CharacterActivity.this, "document does not exists", Toast.LENGTH_SHORT);
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CharacterActivity.this, "Error!", Toast.LENGTH_SHORT);
                        Log.d(TAG,e.toString());
                    }
                });
        }
}




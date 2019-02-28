package com.example.patry.rpgmobilegame.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
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

import javax.annotation.Nullable;

public class CharacterActivity extends AppCompatActivity {
    private static final String TAG = "CharacterActivity";

    private static final String KEY_NAME = "Name";
    private static final String KEY_STR = "Strength";
    private static final String KEY_AGL = "Agility";
    private static final String KEY_VIT = "Vitality";

    private EditText characterName;
    //temp
    private String UID = "mfQUhMQHJs2iNPiPK7rc";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference charRef = db.collection("users").document(UID).collection("Character").document("Stats");

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        characterName =  findViewById(R.id.NameText);

        //loadCharacterData();

    }

    // Listener for any data changes in DB, if something changes view will be updated
    @Override
    protected void onStart(){
        super.onStart();
        charRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e != null) {
                    Toast.makeText(CharacterActivity.this, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG,e.toString());
                    return;
                }

                if(documentSnapshot.exists()) {
                    character = new Character(documentSnapshot.getString(KEY_NAME),10,10,10);
                    characterName.setText(character.name);
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




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
import com.google.firebase.firestore.FirebaseFirestore;

public class CharacterActivity extends AppCompatActivity {
    private static final String TAG = "CharacterActivity";

    private static final String KEY_NAME = "Name";

    private EditText characterName;
    //temp
    private String UID = "mfQUhMQHJs2iNPiPK7rc";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference charRef = db.collection("users").document(UID).collection("Character").document("Stats");
    private DocumentReference ref = db.document("users/mfQUhMQHJs2iNPiPK7rc");

    private Character character;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);

        characterName =  findViewById(R.id.NameText);

        loadCharacterData();

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




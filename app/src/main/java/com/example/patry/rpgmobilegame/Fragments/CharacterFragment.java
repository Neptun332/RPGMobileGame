package com.example.patry.rpgmobilegame.Fragments;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.player.Character;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;

import static com.example.patry.rpgmobilegame.player.Character.KEY_AGL;
import static com.example.patry.rpgmobilegame.player.Character.KEY_NAME;
import static com.example.patry.rpgmobilegame.player.Character.KEY_STR;
import static com.example.patry.rpgmobilegame.player.Character.KEY_VIT;
import static java.lang.Math.toIntExact;


public class CharacterFragment extends Fragment {
    private static final String TAG = "CharacterFragment";

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // View objects
    private TextView characterName;
    private TextView characterStr;
    private TextView characterAgl;
    private TextView characterVit;

    private String UID;
    private DocumentReference charRef;
    private Character character;

    private View view;
    private Activity currentActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_character, container, false);
        characterName = view.findViewById(R.id.nameText);
        characterStr = view.findViewById(R.id.strText);
        characterAgl = view.findViewById(R.id.aglText);
        characterVit = view.findViewById(R.id.vitText);

        UID = firebaseAuth.getCurrentUser().getUid();
        charRef = db.collection("users").document(UID).collection("Character").document("Stats");

        return view;
    }

    // Listener for any data changes in DB, if something changes view will be updated
    @Override
    public void onStart() {
        super.onStart();
        currentActivity = getActivity();

        charRef.addSnapshotListener(currentActivity, new EventListener<DocumentSnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(currentActivity, "Error while loading!", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                    return;
                }

                if (documentSnapshot.exists()) {
                    Map<String, Object> charMap = documentSnapshot.getData();
                    character = new Character((String) charMap.get(KEY_NAME), toIntExact((long) charMap.get(KEY_STR)), toIntExact((long) charMap.get(KEY_AGL)), toIntExact((long) charMap.get(KEY_VIT)));
                    // character = new Character((String) charMap.get(KEY_NAME),10,10,10);
                    characterName.setText("Name: " + character.name);
                    characterStr.setText("Strength: " + character.strength);
                    characterAgl.setText("Agility: " + character.agility);
                    characterVit.setText("Vitality: " + character.vitality);

                } else {
                    Toast.makeText(currentActivity, "document does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}




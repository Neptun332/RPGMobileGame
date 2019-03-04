package com.example.patry.rpgmobilegame.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.player.Character;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.Map;

import javax.annotation.Nullable;

import static com.example.patry.rpgmobilegame.player.Character.KEY_AGL;
import static com.example.patry.rpgmobilegame.player.Character.KEY_INT;
import static com.example.patry.rpgmobilegame.player.Character.KEY_NAME;
import static com.example.patry.rpgmobilegame.player.Character.KEY_STR;
import static com.example.patry.rpgmobilegame.player.Character.KEY_VIT;
import static com.example.patry.rpgmobilegame.player.Character.KEY_WIS;
import static java.lang.Math.toIntExact;

public class CharacterFragment extends Fragment {
    private static final String TAG = "CharacterFragment";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth firebaseAuth;

    // View objects
    private TextView characterName;
    private TextView characterStr;
    private TextView characterAgl;
    private TextView characterVit;
    private TextView characterInt;
    private TextView characterWis;

    private Button eqButton;

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
        characterInt = view.findViewById(R.id.intText);
        characterWis = view.findViewById(R.id.wisText);

        eqButton = view.findViewById(R.id.eqButton);
        eqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment newFragment = new EquipmentFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        currentActivity = getActivity();
        // Data will be load when auth have been initialized
        firebaseAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                LoadUserData();
            }
        });
    }

    void LoadUserData() {
        UID = FirebaseAuth.getInstance().getUid();
        if (UID != null){
            charRef = db.collection("Users").document(UID).collection("Character").document("Stats");
        }
        else {
            Toast.makeText(currentActivity, "UID is NULL", Toast.LENGTH_SHORT).show();
            return;
        }

        // Listener for any data changes in DB, if something changes view will be updated
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
                    character = new Character((String) charMap.get(KEY_NAME), toIntExact((long) charMap.get(KEY_STR)), toIntExact((long) charMap.get(KEY_AGL)), toIntExact((long) charMap.get(KEY_VIT)),toIntExact((long) charMap.get(KEY_INT)),toIntExact((long) charMap.get(KEY_WIS)));
                    // character = new Character((String) charMap.get(KEY_NAME),10,10,10);
                    characterName.setText("Name: " + character.name);
                    characterStr.setText("Strength: " + character.strength);
                    characterAgl.setText("Agility: " + character.agility);
                    characterVit.setText("Vitality: " + character.vitality);
                    characterInt.setText("Intelligence: " + character.intelligence);
                    characterWis.setText("Wisdom: " + character.wisdom);
                } else {
                    Toast.makeText(currentActivity, "document does not exists", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}




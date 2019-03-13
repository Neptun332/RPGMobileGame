package com.example.patry.rpgmobilegame.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.patry.rpgmobilegame.Adventure.AdventureAdapter;
import com.example.patry.rpgmobilegame.PopulateDataBase;
import com.example.patry.rpgmobilegame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class AdventureFragment extends Fragment {

    public static final String TAG = "AdventureFragment";

    private List<Map<String, Object>> adventureList = new ArrayList();
    private AdventureAdapter adapter;
    private RecyclerView recyclerView;
    private View view;
    private RecyclerView.LayoutManager mLayoutManager;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Map<String, Object> AdventureMap = new HashMap<>();
    private ProgressBar progressBar;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adventure, container, false);
        progressBar = view.findViewById(R.id.progressBar_circular);
        PopulateDataBase populateDataBase = new PopulateDataBase();
        //populateDataBase.populateAdventures();
        populateDataBase.populateItems();
        progressBar.setVisibility(View.VISIBLE);
        CollectionReference colRef = db.collection("Adventure");
        colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            Map<String, Object> AdventureMap;
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AdventureMap = new HashMap<>();
                        AdventureMap.putAll(document.getData());
                        adventureList.add(AdventureMap);
                    }
                    recyclerView = view.findViewById(R.id.recycler_view);
                    recyclerView.setHasFixedSize(true);
                    mLayoutManager = new LinearLayoutManager(getContext());
                    adapter = new AdventureAdapter(adventureList);

                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new AdventureAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            //Adventure adventure = adventureList.get(position);
                            //TODO wybranie wycieczki i uruchomienie ekranu walki
                            Toast.makeText(getContext(), "klik", Toast.LENGTH_SHORT).show();
                        }
                    });
                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "failed loading adventures", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "get failed with ", task.getException());
                    progressBar.setVisibility(View.GONE);
                }
            }
        });



        return view;
    }


}

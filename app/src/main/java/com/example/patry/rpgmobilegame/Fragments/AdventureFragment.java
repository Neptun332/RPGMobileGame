package com.example.patry.rpgmobilegame.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patry.rpgmobilegame.Adventure;
import com.example.patry.rpgmobilegame.AdventureAdapter;
import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.activities.GameActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class AdventureFragment extends Fragment {

    private List<Adventure> adventureList = new ArrayList();
    private final AdventureAdapter adapter = new AdventureAdapter();
    private RecyclerView recyclerView;
    private View view;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_adventure, container, false);

        Adventure adventureForrest = new Adventure("Forrest", "Lvl 1-10", "Easy");
        Adventure adventureMine = new Adventure("Mine", "Lvl 10-20", "Medium");
        Adventure adventureCity = new Adventure("City", "Lvl 20-30", "Hard");

        adventureList.add(adventureForrest);
        adventureList.add(adventureMine);
        adventureList.add(adventureCity);


        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager();
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        recyclerView = view.getRootView().findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        adapter.setAdventures(adventureList);

        adapter.setOnItemClickListener(new AdventureAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Adventure adventure = adventureList.get(position);
                //TODO wybranie wycieczki i uruchomienie ekranu walki
            }
        });
        return view;
    }



}

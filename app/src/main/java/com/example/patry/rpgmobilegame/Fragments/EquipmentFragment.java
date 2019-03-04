package com.example.patry.rpgmobilegame.Fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.activities.GameActivity;
import com.example.patry.rpgmobilegame.player.EquipmentAdapter;
import com.example.patry.rpgmobilegame.player.EquipmentItem;

import java.util.ArrayList;

public class EquipmentFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_equipment,container,false);

        ArrayList<EquipmentItem> equipmentItemArrayList = new ArrayList<>();
        equipmentItemArrayList.add(new EquipmentItem(R.drawable.ic_temp_item,"Line 1", "Line 2"));
        equipmentItemArrayList.add(new EquipmentItem(R.drawable.ic_temp_item,"Line 2", "Line 3"));
        equipmentItemArrayList.add(new EquipmentItem(R.drawable.ic_temp_item,"Line 4", "Line 5"));

        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new EquipmentAdapter(equipmentItemArrayList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}

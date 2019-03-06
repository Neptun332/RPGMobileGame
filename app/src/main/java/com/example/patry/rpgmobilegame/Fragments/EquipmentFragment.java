package com.example.patry.rpgmobilegame.Fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.player.EquipmentAdapter;
import com.example.patry.rpgmobilegame.player.EquipmentItem;

import java.util.ArrayList;

public class EquipmentFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_equipment,container,false);

        ArrayList<EquipmentItem> equipmentItems = new ArrayList<>();
        equipmentItems.add(new EquipmentItem(R.drawable.ic_temp_item,"Basic sword", "Damage: 1-2",true ));
        equipmentItems.add(new EquipmentItem(R.drawable.ic_temp_item,"Basic armor", "Armor: 1",false));
        equipmentItems.add(new EquipmentItem(R.drawable.ic_temp_item,"Basic shield", "Block: 10%",false));

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new EquipmentAdapter(equipmentItems);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}

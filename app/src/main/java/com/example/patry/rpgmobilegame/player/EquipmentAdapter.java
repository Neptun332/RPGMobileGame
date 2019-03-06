package com.example.patry.rpgmobilegame.player;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patry.rpgmobilegame.R;

import java.util.ArrayList;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
    private ArrayList<EquipmentItem> equipmentItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemIcon;
        public TextView itemName;
        public TextView itemStats;
        public Button equipButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.itemIcon);
            itemName = itemView.findViewById(R.id.itemName);
            itemStats = itemView.findViewById(R.id.itemStats);
            equipButton = itemView.findViewById(R.id.button_equip);
        }
    }

    public EquipmentAdapter(ArrayList<EquipmentItem> equipmentItems) {
        this.equipmentItems = equipmentItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_equipment, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EquipmentItem currentItem = equipmentItems.get(i);

        viewHolder.itemIcon.setImageResource(currentItem.getItemIcon());
        viewHolder.itemName.setText(currentItem.getItemName());
        viewHolder.itemStats.setText(currentItem.getItemStats());
        if(currentItem.getEquiped()){
           viewHolder.equipButton.setText("EQUIPPED");
           viewHolder.equipButton.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return equipmentItems.size();
    }
}

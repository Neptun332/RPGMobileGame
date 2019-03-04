package com.example.patry.rpgmobilegame.player;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.patry.rpgmobilegame.R;

import java.util.ArrayList;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentAdapter.ViewHolder> {
    private ArrayList<EquipmentItem> equipmentItems;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView eqText1;
        public TextView eqText2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            eqText1 = itemView.findViewById(R.id.textView);
            eqText2 = itemView.findViewById(R.id.textView2);
        }
    }

    public EquipmentAdapter(ArrayList<EquipmentItem> equipmentItemArrayList) {
        equipmentItems = equipmentItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.equipment_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        EquipmentItem currentItem = equipmentItems.get(i);

        viewHolder.mImageView.setImageResource(currentItem.getmImageResource());
        viewHolder.eqText1.setText(currentItem.getEqText1());
        viewHolder.eqText2.setText(currentItem.getEqText2());
    }

    @Override
    public int getItemCount() {
        return equipmentItems.size();
    }
}

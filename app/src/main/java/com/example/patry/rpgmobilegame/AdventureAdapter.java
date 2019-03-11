package com.example.patry.rpgmobilegame;

import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.patry.rpgmobilegame.Adventure.KEY_ADVENTURE_DIFFICULTY;
import static com.example.patry.rpgmobilegame.Adventure.KEY_ADVENTURE_NAME;
import static com.example.patry.rpgmobilegame.Adventure.KEY_IMAGE_FILE_ID;
import static com.example.patry.rpgmobilegame.Adventure.KEY__ADVENTURE_LVL_MAX;
import static com.example.patry.rpgmobilegame.Adventure.KEY__ADVENTURE_LVL_MIN;
import static java.lang.Math.toIntExact;

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureHolder> {

    private List<Map<String, Object>> adventures;
    private OnItemClickListener mListener;
    private View itemView;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public AdventureAdapter(List<Map<String, Object>> adventures) {
        this.adventures = adventures;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public AdventureHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_adventure, viewGroup, false);

        return new AdventureHolder(itemView, mListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull AdventureHolder tourHolder, int i) {
        Map<String, Object> adventure = adventures.get(i);

        Adventure currentAdventure = new Adventure(
                (String) adventure.get(KEY_ADVENTURE_NAME)
                , (String) adventure.get(KEY_ADVENTURE_DIFFICULTY)
                , toIntExact((long) adventure.get(KEY_IMAGE_FILE_ID))
                , toIntExact((long) adventure.get(KEY__ADVENTURE_LVL_MIN))
                , toIntExact((long) adventure.get(KEY__ADVENTURE_LVL_MAX)));

        tourHolder.textViewName.setText(currentAdventure.getName());
        tourHolder.textViewLvl.setText(currentAdventure.getLvlMin() + " - " + currentAdventure.getLvlMax() + " lvl");
        tourHolder.textViewDifficulty.setText(currentAdventure.getDifficulty());
        tourHolder.appCompatImageView.setImageResource(currentAdventure.getImageFile());


    }

    @Override
    public int getItemCount() {
        return adventures.size();
    }

    public void setAdventures(List<Map<String, Object>> adventures) {
        this.adventures = adventures;
        notifyDataSetChanged();
    }

    class AdventureHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewLvl;
        private TextView textViewDifficulty;
        private AppCompatImageView appCompatImageView;

        public AdventureHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.adventure_name);
            textViewLvl = itemView.findViewById(R.id.adventure_lvl);
            textViewDifficulty = itemView.findViewById(R.id.adventure_difficulty);
            appCompatImageView = itemView.findViewById(R.id.background_texture);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public View getItemView() {
        return itemView;
    }
}

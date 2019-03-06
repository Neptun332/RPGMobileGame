package com.example.patry.rpgmobilegame;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
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

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureHolder> {

    private List<Adventure> adventures = new ArrayList();
    private OnItemClickListener mListener;
    private View itemView;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public AdventureAdapter(List<Adventure> adventures) {
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

    @Override
    public void onBindViewHolder(@NonNull AdventureHolder tourHolder, int i) {
        Adventure currentAdventure = adventures.get(i);
        tourHolder.textViewName.setText(currentAdventure.getName());
        tourHolder.textViewLvl.setText(currentAdventure.getLvl());
        tourHolder.textViewDifficulty.setText(currentAdventure.getDifficulty());
        tourHolder.appCompatImageView.setImageResource(currentAdventure.getImageFile());


    }

    @Override
    public int getItemCount() {
        return adventures.size();
    }

    public void setAdventures(List<Adventure> adventures) {
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

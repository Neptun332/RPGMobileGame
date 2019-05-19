package com.example.patry.rpgmobilegame.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patry.rpgmobilegame.R;

import javax.annotation.Nullable;

public class FightFragment extends  Fragment{

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fight, container, false);
    }


}

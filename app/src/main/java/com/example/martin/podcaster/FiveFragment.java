package com.example.martin.podcaster;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FiveFragment extends Fragment {


    public FiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for american fragment
        return inflater.inflate(R.layout.fragment_five, container, false);
    }

}

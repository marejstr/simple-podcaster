package com.example.martin.podcaster;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class AddActivity extends AppCompatActivity {
    android.support.v7.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        searchView = findViewById(R.id.searchView);
        searchView.setIconified(false);

        LinearLayout bottomSheetViewGroup = findViewById(R.id.bottom_sheet_player);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetViewGroup);

        /*
        LinearLayout bottomSheetViewGroup = findViewById(R.id.bottom_sheet_player);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetViewGroup);
        */
        final ImageButton sheetPlay = findViewById(R.id.imageButton);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED){
                    sheetPlay.setImageResource(R.drawable.ic_info_outline_black_24dp);
                }
                if (newState == BottomSheetBehavior.STATE_COLLAPSED){
                    sheetPlay.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                // React to dragging events
            }
        });

        SeekBar seekBar = findViewById(R.id.seekBar2);
        seekBar.setProgress(35);
    }

}

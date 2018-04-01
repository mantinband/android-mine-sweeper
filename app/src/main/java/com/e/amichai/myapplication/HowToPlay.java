package com.e.amichai.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class HowToPlay extends AppCompatActivity {
    private boolean iCameFromMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to_play);


        if (Theme.gameStyle.equals("dark")){
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background_dark);
        } else {
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background);
        }
    }

    @Override
    protected void onRestart() {
        if (MainActivity.currentActivity.equals("first timer") && !MainActivity.mediaPlayer.isPlaying()) {
            MainActivity.mediaPlayer.start();
        }
        super.onRestart();
    }
    @Override
    protected void onStop() {
        if (MainActivity.currentActivity.equals("first timer") && MainActivity.mediaPlayer.isPlaying()){
            MainActivity.mediaPlayer.pause();
        }
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        MainActivity.currentActivity = getIntent().getStringExtra("came from");
        super.onBackPressed();
    }
}

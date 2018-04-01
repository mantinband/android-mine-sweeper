package com.e.amichai.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class settingsActivity extends AppCompatActivity {

    public static AudioManager audioManager;

    private Switch soundSwitch;
    private Switch flagModeSwitch;
    private SeekBar backgroundMusicSeekBar;

    public static int curVolume;
    public static int maxVolume;

    private Button resetButton;
    private boolean resetChosen;

    public static boolean soundOn;
    public static boolean flagModeFloatingButton;
    public static float backgroudMusicVolume;

    private TextView totalGamesPlayed;
    private TextView totalGamesWon;

    private TextView numberOfProGamesPlayed;
    private TextView numberOfIntermediateGamesPlayed;
    private TextView numberOfBeginnerGamesPlayed;

    private TextView numberOfProGamesWon;
    private TextView numberOfIntermediateGamesWon;
    private TextView numberOfBeginnerGamesWon;

    private TextView totalWinningPercentage;
    private TextView beginnerWinningPercentage;
    private TextView intermediateWinningPercentage;
    private TextView proWinningPercentage;
    private Button howToPlayButton;
    private Button dontClickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        if (Theme.gameStyle.equals("dark")){
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background_dark);
        } else {
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background);
        }

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        resetChosen = false;
        soundSwitch = (Switch) findViewById(R.id.soundSwitch);
        flagModeSwitch = (Switch) findViewById(R.id.flagModeSwitch);
        backgroundMusicSeekBar = (SeekBar) findViewById(R.id.backgroundMusicSeekBar);
        dontClickButton = (Button) findViewById(R.id.dontClickButton);

        backgroundMusicSeekBar.setMax(maxVolume);
        backgroundMusicSeekBar.setProgress(curVolume);



        resetButton = (Button) findViewById(R.id.resetButton);

        totalGamesPlayed = (TextView) findViewById(R.id.totalGamesPlayed);
        totalGamesWon = (TextView) findViewById(R.id.totalGamesWon);;

        numberOfProGamesPlayed = (TextView) findViewById(R.id.proGamesPlayed);
        numberOfIntermediateGamesPlayed = (TextView) findViewById(R.id.intermediateGamesPlayed);
        numberOfBeginnerGamesPlayed = (TextView) findViewById(R.id.beginnerGamesPlayed);

        numberOfProGamesWon = (TextView) findViewById(R.id.proGamesWon);
        numberOfIntermediateGamesWon = (TextView) findViewById(R.id.intermediateGamesWon);
        numberOfBeginnerGamesWon = (TextView) findViewById(R.id.beginnerGamesWon);

        totalWinningPercentage = (TextView) findViewById(R.id.totalWinningPercentage);
        beginnerWinningPercentage = (TextView) findViewById(R.id.beginnerWinningPercentage);
        intermediateWinningPercentage = (TextView) findViewById(R.id.intermediateWinningPercentage);
        proWinningPercentage = (TextView) findViewById(R.id.proWinningPercentage);

        howToPlayButton = (Button) findViewById(R.id.howToPlayButton);

        totalGamesPlayed.setText("Total games played: " + Integer.toString(MainActivity.gameStats.getNumberOfGamesPlayed()));
        totalGamesWon.setText("Total games won: " + Integer.toString(MainActivity.gameStats.getNumberOfGamesWon()));

        numberOfBeginnerGamesPlayed.setText("Beginner games played: " + Integer.toString(MainActivity.gameStats.getNumberOfBeginnerGamesPlayed()));
        numberOfIntermediateGamesPlayed.setText("Intermediate games played: "+ Integer.toString(MainActivity.gameStats.getNumberOfIntermediateGamesPlayed()));
        numberOfProGamesPlayed.setText("Pro games played: " + Integer.toString(MainActivity.gameStats.getNumberOfProGamesPlayed()));


        numberOfProGamesWon.setText("Pro games won: " + Integer.toString(MainActivity.gameStats.getNumberOfProGamesWon()));
        numberOfIntermediateGamesWon.setText("Intermediate games won: " + Integer.toString(MainActivity.gameStats.getNumberOfIntermediateGamesWon()));
        numberOfBeginnerGamesWon.setText("Beginner games won: " + Integer.toString(MainActivity.gameStats.getNumberOfBeginnerGamesWon()));


        totalWinningPercentage.setText("Total winning %: " + String.format("%.01f", MainActivity.gameStats.getTotalWinningPercentage())+"%");
        beginnerWinningPercentage.setText("Beginner winning %: " + String.format("%.01f", MainActivity.gameStats.getWinningPercentageBeginnerMode())+"%");
        intermediateWinningPercentage.setText("Intermediate winning %: " + String.format("%.01f", MainActivity.gameStats.getWinningPercentageIntermediateMode())+"%");
        proWinningPercentage.setText("Pro winning %: " + String.format("%.01f", MainActivity.gameStats.getWinningPercentageProMode())+"%");

        soundSwitch.setChecked(soundOn);

        flagModeSwitch.setChecked(flagModeFloatingButton);
        soundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                soundOn = b;
            }
        });

        flagModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                flagModeFloatingButton = b;
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetChosen = true;

                AlertDialog alertDialog = new AlertDialog.Builder(settingsActivity.this).create();

                alertDialog.setTitle("WARNING");
                alertDialog.setMessage("Are you sure? All high scores and levels will be reset");
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int j) {
                       getApplicationContext().getSharedPreferences(MainActivity.HIGH_SCORE_FILE_NAME,0).edit().clear().commit();
                        Toast.makeText(getApplicationContext(), "Levels and scores reset", Toast.LENGTH_LONG).show();
                        dataReset();
                    }
                });
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        });

        howToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(settingsActivity.this, HowToPlay.class);
                MainActivity.currentActivity = "first timer";
                intent.putExtra("came from", "settings");
                startActivity(intent);
            }
        });

        backgroundMusicSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i == 0){
                    MainActivity.mediaPlayer.pause();
                    curVolume = i;
                } else {
                    if (!MainActivity.mediaPlayer.isPlaying()){
                        MainActivity.mediaPlayer.start();
                    }
                    float log1 = (float) (Math.log(maxVolume - curVolume) / Math.log(maxVolume));
                    backgroudMusicVolume = 1 - log1;
                    MainActivity.mediaPlayer.setVolume(1 - log1, 1 - log1);
                    curVolume = i;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dontClickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer dontPressSound = MediaPlayer.create(getApplicationContext(),R.raw.dont_click_sound);
                dontPressSound.start();
                dontClickButton.setText("AHHHH");
                ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.backgroundConstraintLayout);
                layout.setBackgroundResource(R.drawable.reset_button);
            }
        });
    }

    private void dataReset() {
        Intent backToMain = new Intent(settingsActivity.this, MainActivity.class);
        setResult(RESULT_OK,backToMain);
        MainActivity.currentActivity = "main";
        backToMain.putExtra("game reset",resetChosen);
        finish();
    }

    private void returnToMain() {
        Intent backToMain = new Intent(settingsActivity.this, MainActivity.class);

        setResult(RESULT_OK, backToMain);
        finish();
    }

    @Override
    protected void onRestart() {
        if (MainActivity.currentActivity.equals("settings") && !MainActivity.mediaPlayer.isPlaying()) {
            MainActivity.mediaPlayer.start();
        }
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(settingsActivity.this, MainActivity.class);
        MainActivity.currentActivity = "main";
        setResult(RESULT_OK, backToMain);
        finish();
    }

    @Override
    protected void onStop() {
        if (!resetChosen && MainActivity.currentActivity.equals("settings") && MainActivity.mediaPlayer.isPlaying()){
            MainActivity.mediaPlayer.pause();
        }
        super.onStop();
    }
}
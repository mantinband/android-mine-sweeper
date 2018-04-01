package com.e.amichai.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class gameThemeActivity extends AppCompatActivity {

    private Button smileyButton;
    private Button trumpButton;
    private Button vitasButton;
    private Button quagmireButton;
    private Button boratButton;
    private Button obamaButton;
    private Button dalaiLamaButton;
    private Button oprahButton;
    private Button timberlakeButton;
    private Button einsteinButton;
    private Button galButton;

    private Button trumpButton_dark;
    private Button vitasButton_dark;
    private Button quagmireButton_dark;
    private Button boratButton_dark;
    private Button obamaButton_dark;
    private Button dalaiLamaButton_dark;
    private Button oprahButton_dark;
    private Button timberlakeButton_dark;
    private Button einsteinButton_dark;
    private Button galButton_dark;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_theme);

        if (Theme.gameStyle.equals("dark")){
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background_dark);
        } else {
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background);
        }

        smileyButton = (Button) findViewById(R.id.smileyButton);
        trumpButton = (Button) findViewById(R.id.trumpButton);
        vitasButton = (Button) findViewById(R.id.vitasButton);
        quagmireButton = (Button) findViewById(R.id.quagmireButton);
        boratButton = (Button) findViewById(R.id.boratButton);
        obamaButton = (Button) findViewById(R.id.obamaButton);
        dalaiLamaButton = (Button) findViewById(R.id.dalaiLamaButton);
        oprahButton = (Button) findViewById(R.id.oprahButton);
        timberlakeButton = (Button) findViewById(R.id.timberlakeButton);
        einsteinButton = (Button) findViewById(R.id.einsteinButton);
        galButton = (Button) findViewById(R.id.galButton);


        trumpButton_dark = (Button) findViewById(R.id.trumpDarkButton);
        vitasButton_dark = (Button) findViewById(R.id.vitasDarkButton);
        quagmireButton_dark = (Button) findViewById(R.id.quagmireDarkButton);
        boratButton_dark = (Button) findViewById(R.id.boratDarkButton);
        obamaButton_dark = (Button) findViewById(R.id.obamaDarkButton);
        dalaiLamaButton_dark = (Button) findViewById(R.id.dalaiLamaDarkButton);
        oprahButton_dark = (Button) findViewById(R.id.oprahDarkButton);
        timberlakeButton_dark = (Button) findViewById(R.id.timberlakeDarkButton);
        einsteinButton_dark = (Button) findViewById(R.id.einsteinDarkButton);
        galButton_dark = (Button) findViewById(R.id.galDarkButton);





        smileyButton.setBackgroundResource(R.drawable.smiley);

        if (GameTheme.trump.getLockedStatus() == true) {
            trumpButton.setBackgroundResource(R.drawable.trump_locked);
        } else if (MainActivity.gameTheme.allModesWon(GameTheme.trump)) {
            trumpButton.setBackgroundResource(R.drawable.trump_sunglasses);
        } else {
            trumpButton.setBackgroundResource(R.drawable.trump);
        }

        if (GameTheme.vitas.getLockedStatus() == true) {
            vitasButton.setBackgroundResource(R.drawable.vitas_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.vitas)) {
            vitasButton.setBackgroundResource(R.drawable.vitas_sunglasses);
        } else {
            vitasButton.setBackgroundResource(R.drawable.vitas);
        }

        if (GameTheme.quagmire.getLockedStatus() == true) {
            quagmireButton.setBackgroundResource(R.drawable.quagmire_locked);;
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.quagmire)) {
            quagmireButton.setBackgroundResource(R.drawable.quagmire_sunglasses);
        }else {
            quagmireButton.setBackgroundResource(R.drawable.quagmire);
        }

        if (GameTheme.borat.getLockedStatus() == true) {
            boratButton.setBackgroundResource(R.drawable.borat_locked);
        } else if (MainActivity.gameTheme.allModesWon(GameTheme.borat)) {
            boratButton.setBackgroundResource(R.drawable.borat_sunglasses);
        } else {
            boratButton.setBackgroundResource(R.drawable.borat);
        }
        if (GameTheme.obama.getLockedStatus() == true) {
            obamaButton.setBackgroundResource(R.drawable.obama_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.obama)) {
            obamaButton.setBackgroundResource(R.drawable.obama_sunglasses);
        }else {
            obamaButton.setBackgroundResource(R.drawable.obama);
        }

        if (GameTheme.dalaiLama.getLockedStatus() == true) {
            dalaiLamaButton.setBackgroundResource(R.drawable.dalai_lama_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.dalaiLama)) {
            dalaiLamaButton.setBackgroundResource(R.drawable.dalai_lama_level_won);
        }else {
            dalaiLamaButton.setBackgroundResource(R.drawable.dalai_lama);
        }

        if (GameTheme.oprah.getLockedStatus() == true) {
            oprahButton.setBackgroundResource(R.drawable.oprah_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.oprah)) {
            oprahButton.setBackgroundResource(R.drawable.oprah_level_won);
        }else {
            oprahButton.setBackgroundResource(R.drawable.oprah_winfrey);
        }

        if (GameTheme.timberlake.getLockedStatus() == true) {
            timberlakeButton.setBackgroundResource(R.drawable.timberlake_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.timberlake)) {
            timberlakeButton.setBackgroundResource(R.drawable.timberlake_level_won);
        }else {
            timberlakeButton.setBackgroundResource(R.drawable.timberlake);
        }

        if (GameTheme.einstein.getLockedStatus() == true) {
            einsteinButton.setBackgroundResource(R.drawable.einstein_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.einstein)) {
            einsteinButton.setBackgroundResource(R.drawable.einstein_level_won);
        }else {
            einsteinButton.setBackgroundResource(R.drawable.einstein);
        }


        if (GameTheme.gal.getLockedStatus() == true) {
            galButton.setBackgroundResource(R.drawable.gal_locked);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.gal)) {
            galButton.setBackgroundResource(R.drawable.gal_sungalsses);
        }else {
            galButton.setBackgroundResource(R.drawable.gal);
        }






        if (GameTheme.trump_dark.getLockedStatus() == true) {
            trumpButton_dark.setBackgroundResource(R.drawable.trump_locked_dark);
        } else if (MainActivity.gameTheme.allModesWon(GameTheme.trump_dark)) {
            trumpButton_dark.setBackgroundResource(R.drawable.trump_sunglasses_dark);
        } else {
            trumpButton_dark.setBackgroundResource(R.drawable.trump_dark);
        }

        if (GameTheme.vitas_dark.getLockedStatus() == true) {
            vitasButton_dark.setBackgroundResource(R.drawable.vitas_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.vitas_dark)) {
            vitasButton_dark.setBackgroundResource(R.drawable.vitas_sunglasses_dark);
        } else {
            vitasButton_dark.setBackgroundResource(R.drawable.vitas_dark);
        }

        if (GameTheme.quagmire_dark.getLockedStatus() == true) {
            quagmireButton_dark.setBackgroundResource(R.drawable.quagmire_locked_dark);;
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.quagmire_dark)) {
            quagmireButton_dark.setBackgroundResource(R.drawable.quagmire_sunglasses_dark);
        }else {
            quagmireButton_dark.setBackgroundResource(R.drawable.quagmire_dark);
        }

        if (GameTheme.borat_dark.getLockedStatus() == true) {
            boratButton_dark.setBackgroundResource(R.drawable.borat_locked_dark);
        } else if (MainActivity.gameTheme.allModesWon(GameTheme.borat_dark)) {
            boratButton_dark.setBackgroundResource(R.drawable.borat_sunglasses_dark);
        } else {
            boratButton_dark.setBackgroundResource(R.drawable.borat_dark);
        }
        if (GameTheme.obama_dark.getLockedStatus() == true) {
            obamaButton_dark.setBackgroundResource(R.drawable.obama_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.obama_dark)) {
            obamaButton_dark.setBackgroundResource(R.drawable.obama_sunglasses_dark);
        }else {
            obamaButton_dark.setBackgroundResource(R.drawable.obama_dark);
        }

        if (GameTheme.dalaiLama_dark.getLockedStatus() == true) {
            dalaiLamaButton_dark.setBackgroundResource(R.drawable.dalai_lama_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.dalaiLama_dark)) {
            dalaiLamaButton_dark.setBackgroundResource(R.drawable.dalai_lama_sunglasses_dark);
        }else {
            dalaiLamaButton_dark.setBackgroundResource(R.drawable.dalai_lama_dark);
        }

        if (GameTheme.oprah_dark.getLockedStatus() == true) {
            oprahButton_dark.setBackgroundResource(R.drawable.oprah_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.oprah_dark)) {
            oprahButton_dark.setBackgroundResource(R.drawable.oprah_sunglasses_dark);
        }else {
            oprahButton_dark.setBackgroundResource(R.drawable.oprah_dark);
        }

        if (GameTheme.timberlake_dark.getLockedStatus() == true) {
            timberlakeButton_dark.setBackgroundResource(R.drawable.timerlake_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.timberlake_dark)) {
            timberlakeButton_dark.setBackgroundResource(R.drawable.timberlake_sunglasses_dark);
        }else {
            timberlakeButton_dark.setBackgroundResource(R.drawable.timberlake_dark);
        }

        if (GameTheme.einstein_dark.getLockedStatus() == true) {
            einsteinButton_dark.setBackgroundResource(R.drawable.einstein_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.einstein_dark)) {
            einsteinButton_dark.setBackgroundResource(R.drawable.einstein_sunglasses_dark);
        }else {
            einsteinButton_dark.setBackgroundResource(R.drawable.einstein_dark);
        }


        if (GameTheme.gal_dark.getLockedStatus() == true) {
            galButton_dark.setBackgroundResource(R.drawable.gal_locked_dark);
        }  else if (MainActivity.gameTheme.allModesWon(GameTheme.gal_dark)) {
            galButton_dark.setBackgroundResource(R.drawable.gal_sunglasses_dark);
        }else {
            galButton_dark.setBackgroundResource(R.drawable.gal_dark);
        }






        smileyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameTheme.currentGameLevel = GameTheme.classic;
                GameTheme.theme.setThemeName("classic");
                if (GameTheme.theme.equals("dark")) {
                    Theme.gameStyle = "classic";
                    changeSong();
                }
                returnToMain();
            }
        });

        trumpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.trump.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.trump;
                    GameTheme.theme.setThemeName("trump");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        vitasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.vitas.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.vitas;
                    GameTheme.theme.setThemeName("vitas");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        quagmireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.quagmire.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.quagmire;
                    GameTheme.theme.setThemeName("quagmire");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        boratButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.borat.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.borat;
                    GameTheme.theme.setThemeName("borat");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        obamaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.obama.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.obama;
                    GameTheme.theme.setThemeName("obama");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        dalaiLamaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.dalaiLama.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.dalaiLama;
                    GameTheme.theme.setThemeName("dalai lama");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        oprahButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.oprah.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.oprah;
                    GameTheme.theme.setThemeName("oprah");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        timberlakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.timberlake.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.timberlake;
                    GameTheme.theme.setThemeName("timberlake");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        einsteinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.einstein.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.einstein;
                    GameTheme.theme.setThemeName("einstein");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        galButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.gal.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.gal;
                    GameTheme.theme.setThemeName("gal");
                    if (Theme.gameStyle.equals("dark")) {
                        Theme.gameStyle = "classic";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });





        trumpButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.trump_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.trump_dark;
                    GameTheme.theme.setThemeName("trump_dark");

                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        vitasButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.vitas_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.vitas_dark;
                    GameTheme.theme.setThemeName("vitas_dark");

                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        quagmireButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.quagmire_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.quagmire_dark;
                    GameTheme.theme.setThemeName("quagmire_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        boratButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.borat_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.borat_dark;
                    GameTheme.theme.setThemeName("borat_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        obamaButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.obama_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.obama_dark;
                    GameTheme.theme.setThemeName("obama_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });

        dalaiLamaButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.dalaiLama_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.dalaiLama_dark;
                    GameTheme.theme.setThemeName("dalai lama dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        oprahButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.oprah_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.oprah_dark;
                    GameTheme.theme.setThemeName("oprah_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        timberlakeButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.timberlake_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.timberlake_dark;
                    GameTheme.theme.setThemeName("timberlake_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        einsteinButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.einstein_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.einstein_dark;
                    GameTheme.theme.setThemeName("einstein_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
        galButton_dark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameTheme.gal_dark.getLockedStatus() == true){
                    levelLockedMessage();
                } else {
                    GameTheme.currentGameLevel = GameTheme.gal_dark;
                    GameTheme.theme.setThemeName("gal_dark");
                    if (Theme.gameStyle.equals("classic")) {
                        Theme.gameStyle = "dark";
                        changeSong();
                    }
                    returnToMain();
                }
            }
        });
    }

    private void changeSong() {
        MainActivity.mediaPlayer.stop();
        MainActivity.mediaPlayer = null;

        if (Theme.gameStyle.equals("dark")) {
            MainActivity.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.backgroud_music_dark);
        } else {
            MainActivity.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.background_music);
        }

        MainActivity.mediaPlayer.start();
        MainActivity.mediaPlayer.setLooping(true);
    }

    private void levelLockedMessage() {
        Toast.makeText(getApplicationContext(), "Level locked", Toast.LENGTH_SHORT).show();
    }

    private void returnToMain() {
        Intent backToMain = new Intent(gameThemeActivity.this, MainActivity.class);
        MainActivity.currentActivity = "main";
        setResult(RESULT_OK, backToMain);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(gameThemeActivity.this, MainActivity.class);
        MainActivity.currentActivity = "main";
        setResult(RESULT_OK, backToMain);
        finish();
    }
    @Override
    protected void onStop() {
        if (MainActivity.currentActivity.equals("theme") && MainActivity.mediaPlayer.isPlaying()){
            MainActivity.mediaPlayer.pause();
        }
        super.onStop();
    }
    @Override
    protected void onRestart() {
        if (MainActivity.currentActivity.equals("theme") && !MainActivity.mediaPlayer.isPlaying()) {
            MainActivity.mediaPlayer.start();
        }
        super.onRestart();
    }
}

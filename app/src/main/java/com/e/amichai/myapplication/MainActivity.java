package com.e.amichai.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String HIGH_SCORE_FILE_NAME = "highScores";
    private AudioManager audioManager;

    public static String currentActivity;

    private Button beginnerButton;
    private Button intermediateButton;
    private Button proButton;
    private Switch customSwitch;

    private Spinner boardSizeSpinner;
    private Spinner numberOfMinesSpinner;

    private Button startGameButton;
    private Button settingsButton;
    private Button gameThemeButton;
    private Button firstTimerButton;

    private int boardSize;
    private int numberOfMines;
    private int gameTime;
    private int spinnerBoardSizeSelected;
    private int spinnerNumberOfMinesSelected;

    private TextView boardSizeTextView;
    private TextView minesTextView;

    public static GameTheme gameTheme;

    ArrayList<String> numOfMines;

    public static String gameMode;

    public static MediaPlayer mediaPlayer;

    private ImageView arrowAnimationImageView;

    private Animations animation;

    private Thread t;

    public static Statistics gameStats;

    private SharedPreferences highScores;

    private Animation fadeInAnimation;
    private Animation fadeOutAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-9056258295474141~8159201405");




        Theme.gameStyle = "classic";
        settingsActivity.levelSounds = true;
        currentActivity = "main";

        highScores = getSharedPreferences(HIGH_SCORE_FILE_NAME, Context.MODE_PRIVATE);

        fadeInAnimation = AnimationUtils.loadAnimation(this,R.anim.fadein);
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        gameTheme = new  GameTheme();
        gameStats = new Statistics();
        animation = new  Animations();

        setLevelOrder();

        settingsActivity.soundOn = true;
        settingsActivity.flagModeFloatingButton = true;
        getHighScores();
        getStats();

        setButtons();

        addBestTimeToButtons();
        setButtonsBackground();
        arrowAnimationImageView = (ImageView) findViewById(R.id.arrowAnimationImageView);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        settingsActivity.curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        settingsActivity.maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        float log1=(float)(Math.log(settingsActivity.maxVolume -settingsActivity.curVolume)/Math.log(settingsActivity.maxVolume ));
        settingsActivity.backgroudMusicVolume = 1-log1;

        mediaPlayer.setVolume(1-log1,1-log1);
        mediaPlayer.start();

        mediaPlayer.setLooping(true);

        setArrowAnimation();
        t.start();
    }

    @Override
    protected void onRestart() {
        if (currentActivity.equals("main") && !mediaPlayer.isPlaying() && settingsActivity.curVolume != 0 && settingsActivity.soundOn){
            mediaPlayer.start();
        }
        super.onRestart();
    }

    @Override
    protected void onStop(){
        if (currentActivity.equals("main") && mediaPlayer.isPlaying() ){
            mediaPlayer.pause();
        }
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        if (currentActivity.equals("main") && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        super.onBackPressed();
    }

    private void setArrowAnimation() {
        t = new Thread(){
            @Override
            public void run() {
                try{
                    while (!isInterrupted()){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (currentActivity.equals("main")) {
                                    animation.arrow(arrowAnimationImageView);
                                }
                            }
                        });
                        Thread.sleep(100);
                    }
                } catch (InterruptedException e){

                }
            }
        };
    }

    private void setButtons() {
        boardSizeTextView = (TextView) findViewById(R.id.boardSizeTextView);
        minesTextView = (TextView) findViewById(R.id.minesTextView);

        firstTimerButton = (Button) findViewById(R.id.firstTimerButton);
        firstTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentActivity = "first timer";
                Intent intent = new Intent(MainActivity.this,  HowToPlay.class);
                intent.putExtra("came from", "main");
                startActivity(intent);
            }
        });

        beginnerButton = (Button) findViewById(R.id.easyButton);
        beginnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMode = "easy";
                boardSize =  GameTheme.currentGameLevel.getBoardSizeEasyMode();
                numberOfMines =  GameTheme.currentGameLevel.getNumberOfBombsEasyMode();
                startGame();
            }
        });
        intermediateButton = (Button) findViewById(R.id.intermediateButton);
        intermediateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMode = "intermediate";
                boardSize =  GameTheme.currentGameLevel.getBoardSizeIntermediateMode();
                numberOfMines =  GameTheme.currentGameLevel.getNumberOfBombsIntermediateMode();
                startGame();
            }
        });


        proButton = (Button) findViewById(R.id.proButton);
        proButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMode = "pro";
                boardSize =  GameTheme.currentGameLevel.getBoardSizeProMode();
                numberOfMines =  GameTheme.currentGameLevel.getNumberOfBombsHardMode();
                startGame();
            }
        });

        numOfMines = new ArrayList<String>();

        boardSizeSpinner = (Spinner) findViewById(R.id.boardSizeSpinner);
        numberOfMinesSpinner = (Spinner) findViewById(R.id.numberOfMinesSpinner);

        customSwitch = (Switch) findViewById(R.id.customSwitch);
        customSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    startGameButton.startAnimation(fadeInAnimation);
                    boardSizeSpinner.startAnimation(fadeInAnimation);
                    numberOfMinesSpinner.startAnimation(fadeInAnimation);
                    boardSizeTextView.startAnimation(fadeInAnimation);
                    minesTextView.startAnimation(fadeInAnimation);

                    spinnerBoardSizeSelected = 3;
                    spinnerNumberOfMinesSelected = 1;

                    startGameButton.setVisibility(View.VISIBLE);
                    boardSizeSpinner.setVisibility(View.VISIBLE);
                    numberOfMinesSpinner.setVisibility(View.VISIBLE);
                    boardSizeTextView.setVisibility(View.VISIBLE);
                    minesTextView.setVisibility(View.VISIBLE);
                } else {
                    startGameButton.startAnimation(fadeOutAnimation);
                    boardSizeSpinner.startAnimation(fadeOutAnimation);
                    numberOfMinesSpinner.startAnimation(fadeOutAnimation);
                    boardSizeTextView.startAnimation(fadeOutAnimation);
                    minesTextView.startAnimation(fadeOutAnimation);

                    startGameButton.setVisibility(View.INVISIBLE);
                    boardSizeSpinner.setVisibility(View.INVISIBLE);
                    numberOfMinesSpinner.setVisibility(View.INVISIBLE);
                    boardSizeTextView.setVisibility(View.INVISIBLE);
                    minesTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

        boardSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerBoardSizeSelected = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                setNumberOfMinesInSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerBoardSizeSelected = 3;
                setNumberOfMinesInSpinner();
            }
        });


        numberOfMinesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerNumberOfMinesSelected = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        startGameButton = (Button) findViewById(R.id.startGameButton);
        startGameButton.setVisibility(View.INVISIBLE);

        startGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameMode = "custom";
                boardSize = spinnerBoardSizeSelected;
                numberOfMines = spinnerNumberOfMinesSelected;
                startGame();
            }
        });

        settingsButton = (Button) findViewById(R.id.settingsButton);
        settingsButton.setBackgroundResource(R.drawable.settings_icon);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentActivity = "settings";
                Intent i = new Intent(MainActivity.this, settingsActivity.class);
                startActivityForResult(i, 1);
            }
        });

        gameThemeButton = (Button) findViewById(R.id.gameThemeButton);
         GameTheme.theme.setThemeButton(gameThemeButton);

        gameThemeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentActivity = "theme";
                Intent i = new Intent(MainActivity.this,  gameThemeActivity.class);
                startActivityForResult(i, 3);
            }
        });
    }
    private void showAllModeButtons() {
        beginnerButton.setVisibility(View.VISIBLE);
        intermediateButton.setVisibility(View.VISIBLE);
        proButton.setVisibility(View.VISIBLE);
        customSwitch.setVisibility(View.VISIBLE);
    }

    private void hideAllModeButtons() {
        beginnerButton.setVisibility(View.INVISIBLE);
        intermediateButton.setVisibility(View.INVISIBLE);
        proButton.setVisibility(View.INVISIBLE);
        customSwitch.setVisibility(View.INVISIBLE);
    }

    private void getStats() {
        gameStats.setNumberOfBeginnerGamesPlayed(highScores.getInt("beginner games played",0));
        gameStats.setNumberOfIntermediateGamesPlayed(highScores.getInt("intermediate games played",0));
        gameStats.setNumberOfProGamesPlayed(highScores.getInt("pro games played",0));

        gameStats.setNumberOfBeginnerGamesWon(highScores.getInt("beginner games won",0));
        gameStats.setNumberOfIntermediateGamesWon(highScores.getInt("intermediate games won",0));
        gameStats.setNumberOfProGamesWon(highScores.getInt("pro games won",0));
    }


    private void getHighScores() {
        for (int i = 0; i <  GameTheme.NUMBER_OF_LEVELS; i++) {
             GameTheme.gameLevels.get(i).setBestTimeEasyMode(highScores.getInt( GameTheme.gameLevels.get(i).getThemeName() + " easy", Integer.MAX_VALUE));
             GameTheme.gameLevels.get(i).setBestIntermediateMode(highScores.getInt( GameTheme.gameLevels.get(i).getThemeName() + " intermediate", Integer.MAX_VALUE));
             GameTheme.gameLevels.get(i).setBestTimeProMode(highScores.getInt( GameTheme.gameLevels.get(i).getThemeName() + " pro", Integer.MAX_VALUE));
        }
         GameTheme.currentGameLevel =  GameTheme.classic;

        while ( GameTheme.currentGameLevel.hasNext()){
            if (gameTheme.allModesWon( GameTheme.currentGameLevel)){
                 GameTheme.currentGameLevel.getNextLevel().setLockedStatus(false);
            }
             GameTheme.currentGameLevel =  GameTheme.currentGameLevel.getNextLevel();
        }

         GameTheme.currentGameLevel =  GameTheme.classic;

    }



    private void setLevelOrder() {
        int i;
        for (i=0; i<  GameTheme.NUMBER_OF_LEVELS-1; i++){
            GameTheme.gameLevels.get(i+1).setPreviousLevel(GameTheme.gameLevels.get(i));
            GameTheme.gameLevels.get(i).setNextLevel( GameTheme.gameLevels.get(i+1));
        }
        gameTheme.gameLevels.get(i).setNextLevel(null);
    }

    public void addBestTimeToButtons() {
        if ( GameTheme.currentGameLevel.getBestTimeEasyMode() != Integer.MAX_VALUE){
            beginnerButton.setText("EASY\nBest time: " + Integer.toString(GameTheme.currentGameLevel.getBestTimeEasyMode() / 60) + ":" + (GameTheme.currentGameLevel.getBestTimeEasyMode() % 60 > 9 ? Integer.toString(GameTheme.currentGameLevel.getBestTimeEasyMode()% 60) : "0" + Integer.toString(GameTheme.currentGameLevel.getBestTimeEasyMode() % 60)));
        } else {
            beginnerButton.setText("EASY");
        }

        if ( GameTheme.currentGameLevel.getBestIntermediateMode() != Integer.MAX_VALUE){
            intermediateButton.setText("INTERMEDIATE\nBest time: " + Integer.toString(GameTheme.currentGameLevel.getBestIntermediateMode() / 60) + ":" + (GameTheme.currentGameLevel.getBestIntermediateMode()% 60 > 9 ? Integer.toString(GameTheme.currentGameLevel.getBestIntermediateMode()% 60) : "0" + Integer.toString(GameTheme.currentGameLevel.getBestIntermediateMode() % 60)));
        } else {
            intermediateButton.setText("INTERMEDIATE");
        }

        if ( GameTheme.currentGameLevel.getBestTimeProMode() != Integer.MAX_VALUE){
            proButton.setText("PRO\nBest time: " + Integer.toString(GameTheme.currentGameLevel.getBestTimeProMode() / 60) + ":" + (GameTheme.currentGameLevel.getBestTimeProMode()% 60 > 9 ? Integer.toString(GameTheme.currentGameLevel.getBestTimeProMode()% 60) : "0" + Integer.toString(GameTheme.currentGameLevel.getBestTimeProMode() % 60)));;
        } else {
            proButton.setText("PRO");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 1){
                if (data.getBooleanExtra("game reset",false)){
                    gameReset();
                }
            }
            if (requestCode == 2) {
                updateStats(data.getBooleanExtra("gameWon",false),data.getBooleanExtra("gameLost",false));
                GameTheme.theme.setThemeButton(gameThemeButton);
                if (data.getBooleanExtra("gameWon", false)) {
                    gameTime = data.getIntExtra("time", 0);
                    if(gameTheme.allModesWon( GameTheme.currentGameLevel)){
                        openNextLevelIfLocked();
                    }
                    saveHighScore();
                }
                if (!SecondActivity.alertDialogChoice.equals("back to home")){
                    executeAlertDialogChoice();
                }
            }
            if (requestCode == 3){
                 GameTheme.theme.setThemeButton(gameThemeButton);
                if (Theme.gameStyle.equals("dark")){
                    findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background_dark);
                    if (customSwitch.getVisibility() == View.VISIBLE) {
                        customSwitch.setVisibility(View.INVISIBLE);
                    }
                } else {
                    findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background);
                    if (customSwitch.getVisibility() == View.INVISIBLE) {
                        customSwitch.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
        setButtonsBackground();
        addBestTimeToButtons();
    }

    private void executeAlertDialogChoice() {

        switch (SecondActivity.alertDialogChoice){
            case "refresh":
                startGame();
                break;
            case "previous level":
                startPreviousLevel();
                break;
            case "next level":
                startNextLevel();
                break;
        }
    }

    private void startNextLevel() {
        switch (gameMode){
            case "easy":
                intermediateButton.callOnClick();
                break;
            case "intermediate":
                proButton.callOnClick();
                break;
            case "pro":
                GameTheme.currentGameLevel = GameTheme.currentGameLevel.getNextLevel();
                GameTheme.theme.setThemeName(GameTheme.currentGameLevel.getThemeName());
                beginnerButton.callOnClick();
                break;
        }
    }

    private void startPreviousLevel() {
        switch (gameMode){
            case "easy":
                GameTheme.currentGameLevel = GameTheme.currentGameLevel.getPreviousLevel();
                GameTheme.theme.setThemeName(GameTheme.currentGameLevel.getThemeName());
                proButton.callOnClick();
                break;
            case "intermediate":
                beginnerButton.callOnClick();
                break;
            case "pro":
                intermediateButton.callOnClick();
                break;
        }
    }

    private void gameReset() {
        gameStats.resetData();
        getHighScores();
        setButtonsBackground();
        addBestTimeToButtons();
        lockLevels();
        Intent i = new Intent(MainActivity.this, settingsActivity.class);
        currentActivity = "settings";
        startActivityForResult(i,1);
    }

    private void lockLevels() {
        for (int i = 2; i<  GameTheme.NUMBER_OF_LEVELS; i++){
             GameTheme.gameLevels.get(i).setLockedStatus(true);
        }
    }

    private void    updateStats(boolean gameWon, boolean gameLost) {
        if (!gameWon && !gameLost){
            return;
        }
        switch (gameMode){
            case "easy":
                gameStats.setNumberOfBeginnerGamesPlayed(gameStats.getNumberOfBeginnerGamesPlayed()+1);
                highScores.edit().putInt("beginner games played",gameStats.getNumberOfBeginnerGamesPlayed()).apply();

                if (gameWon){
                    gameStats.setNumberOfBeginnerGamesWon(gameStats.getNumberOfBeginnerGamesWon()+1);
                    highScores.edit().putInt("beginner games won",gameStats.getNumberOfBeginnerGamesWon()).apply();
                }
                break;

            case "intermediate":
                gameStats.setNumberOfIntermediateGamesPlayed(gameStats.getNumberOfIntermediateGamesPlayed()+1);
                highScores.edit().putInt("intermediate games played",gameStats.getNumberOfIntermediateGamesPlayed()).apply();

                if (gameWon){
                    gameStats.setNumberOfIntermediateGamesWon(gameStats.getNumberOfIntermediateGamesWon()+1);
                    highScores.edit().putInt("intermediate games won",gameStats.getNumberOfIntermediateGamesWon()).apply();
                }
                break;

            case "pro":
                gameStats.setNumberOfProGamesPlayed(gameStats.getNumberOfProGamesPlayed()+1);
                highScores.edit().putInt("pro games played",gameStats.getNumberOfProGamesPlayed()).apply();

                if (gameWon){
                    gameStats.setNumberOfProGamesWon(gameStats.getNumberOfProGamesWon()+1);
                    highScores.edit().putInt("pro games won",gameStats.getNumberOfProGamesWon()).apply();
                }
        }
    }

    private void saveHighScore() {
        SharedPreferences highScores = getSharedPreferences(HIGH_SCORE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = highScores.edit();



        editor.putInt( GameTheme.currentGameLevel.getThemeName() + " easy",  GameTheme.currentGameLevel.getBestTimeEasyMode());
        editor.putInt( GameTheme.currentGameLevel.getThemeName() + " intermediate",  GameTheme.currentGameLevel.getBestIntermediateMode());
        editor.putInt( GameTheme.currentGameLevel.getThemeName() + " pro",  GameTheme.currentGameLevel.getBestTimeProMode());
        editor.apply();

    }

    /*
key                                     value
"hosam malek number of messages:"       30
"1 date"                                "04042010"
"1 content"                             "hi how are you"


*/
    private void openNextLevelIfLocked() {
        if ( GameTheme.currentGameLevel.hasNext() &&  GameTheme.currentGameLevel.getNextLevel().getLockedStatus() == true){
             GameTheme.currentGameLevel.getNextLevel().setLockedStatus(false);
            Toast.makeText(getApplicationContext(),  "Next level unlocked!",Toast.LENGTH_LONG).show();
        }
    }

    private void setButtonsBackground() {
        if ( GameTheme.currentGameLevel.getBestTimeEasyMode() < Integer.MAX_VALUE) {
            beginnerButton.setBackgroundResource(R.drawable.won);
        } else {
            beginnerButton.setBackgroundResource(R.drawable.unwon);
        }
        if ( GameTheme.currentGameLevel.getBestIntermediateMode() < Integer.MAX_VALUE){
            intermediateButton.setBackgroundResource(R.drawable.won);
        } else {
            intermediateButton.setBackgroundResource(R.drawable.unwon);
        }

        if ( GameTheme.currentGameLevel.getBestTimeProMode() < Integer.MAX_VALUE){
            proButton.setBackgroundResource(R.drawable.won);
        } else {
            proButton.setBackgroundResource(R.drawable.unwon);
        }
    }



    private void setNumberOfMinesInSpinner() {
        numOfMines.clear();

        for (int i=0; i< spinnerBoardSizeSelected*spinnerBoardSizeSelected-1; i++)
            numOfMines.add(Integer.toString(i+1));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,numOfMines);

        numberOfMinesSpinner.setAdapter(adapter);
    }

    private void startGame() {
        currentActivity = "second";
        Intent game = new Intent(MainActivity.this,  SecondActivity.class);
        game.putExtra("boardSize",boardSize);
        game.putExtra("numberOfMines",numberOfMines);
        startActivityForResult(game,2);
    }


}

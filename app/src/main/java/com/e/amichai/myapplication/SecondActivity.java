package com.e.amichai.myapplication;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

public class SecondActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd mRewardedVideoAd;
    private AlertDialog dialog;
    private Board board;
    private Button[][] buttons;
    private int boardSize;
    private int numberOfMines;
    private TableLayout boardTableLayout;
    private TextView numberOfMinesLeftTextView;
    private TextView numberOfSecondsTextView;
    private FloatingActionButton flagButton;
    private ImageView flagImageView;
    private int time = 0;
    private Button pauseGameButton;

    private int secondsLeftToContinue;
    private boolean timesUp;

    private MediaPlayer startGameMediaPlayer;
    private MediaPlayer winGameMediaPlayer;

    public static String alertDialogChoice;

    private Button smileButton;
    private boolean gamePaused;

    public static Thread t;

    private Button continueGameButton;
    private boolean rewardVideoClicked;

    private Button soundOnOffButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        if (Theme.gameStyle.equals("dark")){
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background_dark);
        } else {
            findViewById(R.id.backgroundConstraintLayout).setBackgroundResource(R.drawable.background);
        }


        AdView mAdView= new AdView(this);
        mAdView.setAdSize(AdSize.BANNER);
        mAdView.setAdUnitId("ca-app-pub-9056258295474141/5602323812");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();

        alertDialogChoice = "back to home";
        gamePaused = false;
        rewardVideoClicked = false;
        Bundle bundle = getIntent().getExtras();
        boardSize = bundle.getInt("boardSize");
        numberOfMines = bundle.getInt("numberOfMines");

        pauseGameButton = (Button) findViewById(R.id.pauseButton);
        numberOfSecondsTextView = (TextView) findViewById(R.id.numberOfSecondsTextView);
        numberOfMinesLeftTextView = (TextView) findViewById(R.id.numberOfMinesLeftTextView);
        flagButton = (FloatingActionButton) findViewById(R.id.flagModeFloatingActionButton);
        flagImageView = (ImageView) findViewById(R.id.flagImageView);
        soundOnOffButton = (Button) findViewById(R.id.soundOnOffButton);
        if (!settingsActivity.flagModeFloatingButton){
            flagButton.setVisibility(View.INVISIBLE);
        }

        setSoundByTheme();



        if (settingsActivity.soundOn){
            soundOnOffButton.setBackgroundResource(R.drawable.sound_on);
        } else {
            soundOnOffButton.setBackgroundResource(R.drawable.sound_off);
        }
        if (settingsActivity.soundOn && !GameTheme.currentGameLevel.getThemeName().contentEquals("classic") && settingsActivity.levelSounds) {
            startGameMediaPlayer.start();
        }

        numberOfMinesLeftTextView.setText(Integer.toString(numberOfMines));
        numberOfSecondsTextView.setText(Integer.toString(time));


        time = 0;
        t = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted() && !board.gameIsOver()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!gamePaused) {
                                    numberOfSecondsTextView.setText(Integer.toString(time));
                                    time += 1;
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch(InterruptedException e){

                }
            }
        };

        board = new Board(boardSize, numberOfMines);
        buttons = new Button[boardSize][boardSize];

        smileButton = (Button) findViewById(R.id.smileButton);
        GameTheme.theme.smileyGameImage(smileButton);

        smileButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    alertDialogChoice = "refresh";
                    board.setGameOver(true);
                    onBackPressed();
                    return true;
                }
                GameTheme.theme.smileyGameImage(smileButton);
                return false;
            }
        });

        flagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (board.getFlagModeStatus()){
                    board.setFlagModeStatus(false);
                    Theme.setFlagButtonImage(flagImageView);
                    Theme.setFlagButtonImage(flagButton);
                } else {
                    board.setFlagModeStatus(true);
                    Theme.setClickedFlagButtonImage(flagImageView);
                    Theme.setClickedFlagButtonImage(flagButton);
                }
            }
        });

        pauseGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gamePaused){
                    unpausedGame();
                } else {
                    pauseGame();
                }
            }
        });


        soundOnOffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (settingsActivity.soundOn){
                    if (!GameTheme.currentGameLevel.getThemeName().equals("classic") && startGameMediaPlayer.isPlaying()){
                        startGameMediaPlayer.stop();
                    }
                    if (!GameTheme.currentGameLevel.getThemeName().equals("classic") && winGameMediaPlayer.isPlaying()){
                        winGameMediaPlayer.stop();
                    }
                    soundOnOffButton.setBackgroundResource(R.drawable.sound_off);
                    settingsActivity.soundOn = false;
                    MainActivity.mediaPlayer.pause();
                } else {
                    soundOnOffButton.setBackgroundResource(R.drawable.sound_on);
                    settingsActivity.soundOn = true;
                    MainActivity.mediaPlayer.start();
                }
            }
        });
        createBoard();
        board.setButtons(buttons);

    }

    private void pauseGame() {
        pauseGameButton.setBackgroundResource(R.drawable.play_icon);
        board.unActivateButtons();
        MainActivity.mediaPlayer.pause();
        gamePaused = true;

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SecondActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.pause_game_alert_dialog, null);
        TextView gameTimeTextView = (TextView) mView.findViewById(R.id.gameTimeTextView);
        Button previousLevelButton = (Button) mView.findViewById(R.id.previousLevelButton);
        Button refreshGameButton = (Button) mView.findViewById(R.id.refreshGameButton);
        Button nextLevelButton = (Button) mView.findViewById(R.id.nextLevelButton);
        Button backToHomeButton = (Button) mView.findViewById(R.id.backToHomeButton);
        Button resumeGameButton = (Button) mView.findViewById(R.id.resumeGameButton);

        switch (MainActivity.gameMode){
            case "easy":
                previousLevelButton.setText("Previous level");
                nextLevelButton.setText("intermediate");
                break;
            case "intermediate":
                previousLevelButton.setText("easy");
                nextLevelButton.setText("pro");
                break;
            case "pro":
                previousLevelButton.setText("intermediate");
                nextLevelButton.setText("next level");
                break;
            case "custom":
                previousLevelButton.setBackgroundColor(0);
                nextLevelButton.setBackgroundColor(0);
                previousLevelButton.setEnabled(false);
                nextLevelButton.setEnabled(false);
                break;
        }


        if (GameTheme.currentGameLevel.getThemeName().equals("classic") && MainActivity.gameMode.equals("easy") || !GameTheme.currentGameLevel.getThemeName().equals("classic") && MainActivity.gameMode.equals("easy") && GameTheme.currentGameLevel.getPreviousLevel().getLockedStatus()) {
            previousLevelButton.setEnabled(false);
            previousLevelButton.setBackgroundColor(0);
        }

        if (MainActivity.gameMode.equals("pro") && GameTheme.currentGameLevel.getThemeName().equals("einstein")){
            nextLevelButton.setEnabled(false);
            nextLevelButton.setBackgroundColor(0);
        }

        previousLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogChoice = "previous level";
                backToMain();

            }
        });

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.gameMode.equals("pro") && !MainActivity.gameTheme.allModesWon(GameTheme.currentGameLevel) && GameTheme.currentGameLevel.hasNext() && GameTheme.currentGameLevel.getNextLevel().getLockedStatus()) {
                    Toast.makeText(getApplicationContext(), "Next level is locked. You must finish 'easy' 'intermediate' and 'pro' in order to open next level.", Toast.LENGTH_LONG).show();
                } else {
                    alertDialogChoice = "next level";
                    backToMain();
                }
            }
        });

        refreshGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogChoice = "refresh";
                backToMain();
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogChoice = "back to home";
                backToMain();
            }
        });

        resumeGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unpausedGame();
                dialog.dismiss();
            }
        });

        String timeInMinutes;
        timeInMinutes = Integer.toString(time / 60) + ":" + (time % 60 > 9 ? Integer.toString(time % 60 - 1) : "0" + Integer.toString(time % 60 - 1 == -1 ? time%60 : time%60-1));
        gameTimeTextView.setText("Time: " + timeInMinutes);

        setAlertDialogBackground(mView);
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
    }

    private void unpausedGame() {
        pauseGameButton.setBackgroundResource(R.drawable.pause_icon);
        gamePaused = false;
        board.reactivateButtons();
        if (!MainActivity.mediaPlayer.isPlaying() && settingsActivity.curVolume != 0 && settingsActivity.soundOn) {
            MainActivity.mediaPlayer.start();
        }
    }

    @Override
    protected void onStop() {
        if (gamePaused){
            if (!alertDialogChoice.equals("back to home") && !MainActivity.mediaPlayer.isPlaying() && settingsActivity.soundOn){
                MainActivity.mediaPlayer.start();
            }
        } else {
            if (MainActivity.currentActivity.equals("second") && !board.gameIsOver()){
                pauseGame();
            }
        }

        if (alertDialogChoice.equals("currently checking") && !MainActivity.currentActivity.equals("main")){
            MainActivity.mediaPlayer.pause();
        }
        super.onStop();
    }

    @Override
    protected void onRestart() {
        if (MainActivity.currentActivity.equals("second") && !MainActivity.mediaPlayer.isPlaying() && !gamePaused && settingsActivity.curVolume != 0) {
            MainActivity.mediaPlayer.start();
        }
        super.onRestart();
    }

    public void createBoard() {
        boardTableLayout = (TableLayout) findViewById(R.id.boardTableLayout);

        for (int row = 0; row < boardSize; row++) {
            TableRow curTableRow = new TableRow(this);
            curTableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            boardTableLayout.addView(curTableRow);
            for (int col = 0; col < boardSize; col++) {
                final Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override

                    public void onClick(View view) {
                        board.reCloseSpots(view.getId()/boardSize,view.getId()%boardSize);
                        GameTheme.theme.smileyGameImage(smileButton);

                        board.buttonClicked(view.getId() / boardSize, view.getId() % boardSize);

                        numberOfMinesLeftTextView.setText(Integer.toString(numberOfMines - board.getSpotsFlagged()));

                        if (board.gameIsOver()) {
                            pauseGameButton.setEnabled(false);
                            board.unActivateButtons();
                            if (board.gameWon()) {
                                if (settingsActivity.soundOn && !GameTheme.currentGameLevel.getThemeName().contentEquals("classic") && settingsActivity.levelSounds){
                                    winGameMediaPlayer.start();
                                }

                                GameTheme.theme.smileyWon(smileButton);
                            } else {
                                GameTheme.theme.smileyLost(smileButton);
                            }
                            alertDialogEndOfGame();
                        }
                    }


                });
                button.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                            GameTheme.theme.smileyPressedImage(smileButton);
                            if (board.buttonIsVisible(view.getId()/boardSize,view.getId()%boardSize)) {
                                return false;
                            }
                            return false;
                        }
                        return false;
                    }
                });
                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        board.reCloseSpots(view.getId()/boardSize,view.getId()%boardSize);
                        GameTheme.theme.smileyGameImage(smileButton);
                        board.buttonLongClicked(view.getId() / boardSize, view.getId() % boardSize);
                        numberOfMinesLeftTextView.setText(Integer.toString(numberOfMines - board.getSpotsFlagged()));
                        return true;
                    }
                });

                button.setId(row * boardSize + col);
                Theme.setUnclickedButtonImage(button);
                curTableRow.addView(button);
                buttons[row][col] = button;

            }
        }
    }
    private void alertDialogEndOfGame() {
        alertDialogChoice = "currently checking";
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SecondActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.game_finished_alert_dialog, null);
        TextView gameFinishedStatusTextView = (TextView) mView.findViewById(R.id.gameFinishedStatusTextView);
        TextView gameTimeTextView = (TextView) mView.findViewById(R.id.gameTimeTextView);
        Button previousLevelButton = (Button) mView.findViewById(R.id.previousLevelButton);
        Button refreshGameButton = (Button) mView.findViewById(R.id.refreshGameButton);
        final Button nextLevelButton = (Button) mView.findViewById(R.id.nextLevelButton);
        Button backToHomeButton = (Button) mView.findViewById(R.id.backToHomeButton);
        continueGameButton = (Button) mView.findViewById(R.id.continueGameButton);
        final Thread countDownThread;

        secondsLeftToContinue = 5;
        timesUp = false;

        countDownThread = new Thread(){
            @Override
            public void run() {
                try {
                    while (!timesUp && !Thread.interrupted())  {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (secondsLeftToContinue >= 0) {
                                    continueGameButton.setText("undo last move?\n" + Integer.toString(secondsLeftToContinue));
                                    secondsLeftToContinue--;
                                } else {
                                    continueGameButton.setVisibility(View.INVISIBLE);
                                    if (!rewardVideoClicked){
                                        board.revealMines();
                                    }
                                    timesUp = true;
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e){
                }
            }
        };


        switch (MainActivity.gameMode){
            case "easy":
                previousLevelButton.setText("Previous level");
                nextLevelButton.setText("intermediate");
                break;
            case "intermediate":
                previousLevelButton.setText("easy");
                nextLevelButton.setText("pro");
                break;
            case "pro":
                previousLevelButton.setText("intermediate");
                nextLevelButton.setText("next level");
                break;
            case "custom":
                previousLevelButton.setBackgroundColor(0);
                nextLevelButton.setBackgroundColor(0);
                previousLevelButton.setEnabled(false);
                nextLevelButton.setEnabled(false);
                break;
        }


        if (GameTheme.currentGameLevel.getThemeName().equals("classic") && MainActivity.gameMode.equals("easy")
                || (!GameTheme.currentGameLevel.getThemeName().equals("classic") && MainActivity.gameMode.equals("easy")
                && GameTheme.currentGameLevel.getPreviousLevel().getLockedStatus()) || (GameTheme.currentGameLevel.getThemeName().equals("trump_dark") && MainActivity.gameMode.equals("easy"))){
            previousLevelButton.setEnabled(false);
            previousLevelButton.setBackgroundColor(0);
        }

        if (MainActivity.gameMode.equals("pro") && GameTheme.currentGameLevel.getThemeName().equals("gal_dark") || GameTheme.currentGameLevel.getThemeName().equals("gal") && MainActivity.gameMode.equals("pro")){
            nextLevelButton.setEnabled(false);
            nextLevelButton.setBackgroundColor(0);
        }

        previousLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogChoice = "previous level";
                backToMain();

            }
        });

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.gameMode.equals("pro") && !MainActivity.gameTheme.allModesWon(GameTheme.currentGameLevel) && GameTheme.currentGameLevel.hasNext() && GameTheme.currentGameLevel.getNextLevel().getLockedStatus()) {
                    Toast.makeText(getApplicationContext(), "Next level is locked. You must finish 'easy' 'intermediate' and 'pro' in order to open next level.", Toast.LENGTH_LONG).show();
                } else {
                    alertDialogChoice = "next level";
                    backToMain();
                }
            }
        });

        refreshGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogChoice = "refresh";
                backToMain();
            }
        });

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogChoice = "back to home";
                backToMain();
            }
        });

        continueGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        });

        String timeInMinutes;
        gameFinishedStatusTextView.setText(board.gameWon() ? newBestTime() ? "NEW BEST TIME!" : "WE HAVE A WINNER!" : "HAHA YOU LOST!");
        timeInMinutes = Integer.toString(time / 60) + ":" + (time % 60 > 9 ? Integer.toString(time % 60 - 1) : "0" + Integer.toString(time % 60 - 1 == -1 ? time%60 : time%60-1));
        gameTimeTextView.setText("Time: " + timeInMinutes);

        if (board.gameIsLost() && mRewardedVideoAd.isLoaded()){
            countDownThread.start();
        } else if (board.gameIsLost()){
            continueGameButton.setVisibility(View.INVISIBLE);
            board.revealMines();
        } else {
            continueGameButton.setVisibility(View.INVISIBLE);
        }
        setAlertDialogBackground(mView);
        mBuilder.setView(mView);

        dialog = mBuilder.create();
        dialog.show();
    }

    private void setAlertDialogBackground(View mView) {
        switch (GameTheme.currentGameLevel.getThemeName()) {
            case "classic":
                mView.setBackgroundResource(R.drawable.smiley);break;
            case "vitas":
            mView.setBackgroundResource(R.drawable.vitas);break;
            case "trump":
                mView.setBackgroundResource(R.drawable.trump);break;
            case "quagmire":
                mView.setBackgroundResource(R.drawable.quagmire);break;
            case "borat":
                mView.setBackgroundResource(R.drawable.borat);break;
            case "obama":
                mView.setBackgroundResource(R.drawable.obama);break;
            case "dalai lama":
                mView.setBackgroundResource(R.drawable.dalai_lama); break;
            case "oprah":
                mView.setBackgroundResource(R.drawable.oprah_winfrey); break;
            case "timberlake":
                mView.setBackgroundResource(R.drawable.timberlake); break;
            case "einstein":
                mView.setBackgroundResource(R.drawable.einstein); break;
            case "gal":
                mView.setBackgroundResource(R.drawable.gal); break;

            case "vitas_dark":
                mView.setBackgroundResource(R.drawable.vitas_dark);break;
            case "trump_dark":
                mView.setBackgroundResource(R.drawable.trump_dark);break;
            case "quagmire_dark":
                mView.setBackgroundResource(R.drawable.quagmire_dark);break;
            case "borat_dark":
                mView.setBackgroundResource(R.drawable.borat_dark);break;
            case "obama_dark":
                mView.setBackgroundResource(R.drawable.obama_dark);break;
            case "dalai lama dark":
                mView.setBackgroundResource(R.drawable.dalai_lama_dark); break;
            case "oprah_dark":
                mView.setBackgroundResource(R.drawable.oprah_dark); break;
            case "timberlake_dark":
                mView.setBackgroundResource(R.drawable.timberlake_dark); break;
            case "einstein_dark":
                mView.setBackgroundResource(R.drawable.einstein_dark); break;
            case "gal_dark":
                mView.setBackgroundResource(R.drawable.gal_dark); break;
        }
    }
    @Override
    public void onResume() {
        mRewardedVideoAd.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewardedVideoAd.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewardedVideoAd.destroy(this);
        super.onDestroy();
    }
    private void loadRewardedVideoAd() {
        mRewardedVideoAd.loadAd("ca-app-pub-9056258295474141/5602323812",
                new AdRequest.Builder().build());
    }

    private boolean newBestTime(){
        if (MainActivity.gameMode == "easy") {
            if ( GameTheme.currentGameLevel.getBestTimeEasyMode() > time) {
                GameTheme.currentGameLevel.setBestTimeEasyMode(time);
                return true;
            }
        } else if (MainActivity.gameMode == "intermediate"){
            if ( GameTheme.currentGameLevel.getBestIntermediateMode() > time){
                GameTheme.currentGameLevel.setBestIntermediateMode(time);
                return true;
            }
        } else if (MainActivity.gameMode == "pro"){
            if ( GameTheme.currentGameLevel.getBestTimeProMode() > time){
                GameTheme.currentGameLevel.setBestTimeProMode(time);
                return true;
            }
        }
        return false;
    }
    private void setSoundByTheme() {
        switch (GameTheme.currentGameLevel.getThemeName()) {
            case "vitas":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_vitas);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_vitas);
                break;
            case "trump":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_trump);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_trump);
                break;
            case "quagmire":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_quagmire);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_quagmire);
                break;
            case "borat":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.start_game_borat);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_borat);
                break;
            case "obama":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.start_game_obama);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_obama);
                break;
            case "dalai lama":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_dalai_lama);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_dame_dalai_lama);
                break;
            case "oprah":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.oprah_start_game);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.oprah_win_game);
                break;
            case "timberlake":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.timberlake_start_game);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.justin_win);
                break;
            case "einstein":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.einstein_start_game);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.einstein_win_game);
                break;
            case "gal":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_gal);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_gal);
                break;
            case "vitas_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_vitas);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_vitas);
                break;
            case "trump_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_trump);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_trump);
                break;
            case "quagmire_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_quagmire);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_quagmire);
                break;
            case "borat_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.start_game_borat);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_borat);
                break;
            case "obama_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.start_game_obama);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_obama);
                break;
            case "dalai lama dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_dalai_lama);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_dame_dalai_lama);
                break;
            case "oprah_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.oprah_start_game);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.oprah_win_game);
                break;
            case "timberlake_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.timberlake_start_game);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.justin_win);
                break;
            case "einstein_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.einstein_start_game);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.einstein_win_game);
                break;
            case "gal_dark":
                startGameMediaPlayer = MediaPlayer.create(this, R.raw.game_start_gal);
                winGameMediaPlayer = MediaPlayer.create(this, R.raw.win_game_gal);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        backToMain();
    }

    private void backToMain() {

        MainActivity.currentActivity = "main";

        Intent backToMain = new Intent(SecondActivity.this, MainActivity.class);

        if (settingsActivity.soundOn && !GameTheme.currentGameLevel.getThemeName().contentEquals("classic")) {
            winGameMediaPlayer.stop();
            startGameMediaPlayer.stop();
        }

        backToMain.putExtra("gameLost", board.gameIsLost());
        if (board.gameWon()){
            backToMain.putExtra("gameWon", true);
            backToMain.putExtra("time", time-1);
            setResult(RESULT_OK, backToMain);
        }

        setResult(RESULT_OK, backToMain);
        finish();
        super.onBackPressed();
    }
    public void onRewarded(RewardItem reward) {
        dialog.dismiss();
        board.undoMineClicked();
        GameTheme.theme.smileyGameImage(smileButton);
        pauseGameButton.setEnabled(true);
        Thread continueTime = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted() && !board.gameIsOver()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!gamePaused) {
                                    numberOfSecondsTextView.setText(Integer.toString(time));
                                    time += 1;
                                }
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch(InterruptedException e){
                }
            }
        };
        continueTime.start();
    }


    @Override
    public void onRewardedVideoAdLeftApplication() {
    }

    @Override
    public void onRewardedVideoAdClosed() {
        continueGameButton.setVisibility(View.INVISIBLE);
        if (!pauseGameButton.isEnabled()){
            board.revealMines();
        }
    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int errorCode) {
    }

    @Override
    public void onRewardedVideoAdLoaded() {
    }

    @Override
    public void onRewardedVideoAdOpened() {
    }

    @Override
    public void onRewardedVideoStarted() {
        rewardVideoClicked = true;
        Toast.makeText(getApplicationContext(),"This lovely ad help me get through collage. Thanks!",Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"Your last move will be undone right after the ad. good luck this time!",Toast.LENGTH_LONG).show();
    }
}

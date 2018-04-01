package com.e.amichai.myapplication;

import java.util.ArrayList;

public class GameTheme {
    public static final int NUMBER_OF_LEVELS = 21;

    public static Theme theme;

    public static final String DEFAULT_THEME= "classic";

    public static ArrayList<GameLevel> gameLevels;

    public static GameLevel classic;
    public static GameLevel trump;
    public static GameLevel vitas;
    public static GameLevel quagmire;
    public static GameLevel borat;
    public static GameLevel obama;
    public static GameLevel dalaiLama;
    public static GameLevel oprah;
    public static GameLevel timberlake;
    public static GameLevel einstein;
    public static GameLevel gal;

    public static GameLevel trump_dark;
    public static GameLevel vitas_dark;
    public static GameLevel quagmire_dark;
    public static GameLevel borat_dark;
    public static GameLevel obama_dark;
    public static GameLevel dalaiLama_dark;
    public static GameLevel oprah_dark;
    public static GameLevel timberlake_dark;
    public static GameLevel einstein_dark;
    public static GameLevel gal_dark;


    public static GameLevel currentGameLevel;

    public GameTheme(){
        theme = new Theme();

        classic = new GameLevel("classic");
        trump = new GameLevel("trump");
        vitas = new GameLevel("vitas");
        quagmire = new GameLevel("quagmire");
        borat = new GameLevel("borat");
        obama = new GameLevel("obama");
        dalaiLama = new GameLevel("dalai lama");
        oprah = new GameLevel("oprah");
        timberlake = new GameLevel("timberlake");
        einstein = new GameLevel("einstein");
        gal = new GameLevel("gal");

        trump_dark = new GameLevel("trump_dark");
        vitas_dark = new GameLevel("vitas_dark");
        quagmire_dark = new GameLevel("quagmire_dark");
        borat_dark = new GameLevel("borat_dark");
        obama_dark = new GameLevel("obama_dark");
        dalaiLama_dark = new GameLevel("dalai lama dark");
        oprah_dark = new GameLevel("oprah_dark");
        timberlake_dark = new GameLevel("timberlake_dark");
        einstein_dark = new GameLevel("einstein_dark");
        gal_dark = new GameLevel("gal_dark");


        gameLevels = new ArrayList<GameLevel>();

        gameLevels.add(0,classic);
        gameLevels.add(1,trump);
        gameLevels.add(2,quagmire);
        gameLevels.add(3,vitas);
        gameLevels.add(4,borat);
        gameLevels.add(5,obama);
        gameLevels.add(6,dalaiLama);
        gameLevels.add(7,oprah);
        gameLevels.add(8,timberlake);
        gameLevels.add(9,einstein);
        gameLevels.add(10,gal);

        gameLevels.add(11,trump_dark);
        gameLevels.add(12,quagmire_dark);
        gameLevels.add(13,vitas_dark);
        gameLevels.add(14,borat_dark);
        gameLevels.add(15,obama_dark);
        gameLevels.add(16,dalaiLama_dark);
        gameLevels.add(17,oprah_dark);
        gameLevels.add(18,timberlake_dark);
        gameLevels.add(19,einstein_dark);
        gameLevels.add(20,gal_dark);

        currentGameLevel = gameLevels.get(0);
        trump.setLockedStatus(false);

        setLevelBoardSizes();
        setLevelNumberOfBombs();
    }

    private void setLevelBoardSizes() {
        for (int i=1; i<NUMBER_OF_LEVELS; i++){
            if (GameLevel.EASY_BOARD_SIZE+i > GameLevel.MAX_BOARD_SIZE){
                gameLevels.get(i).setBoardSizeEasyMode(GameLevel.MAX_BOARD_SIZE);
            } else {
                gameLevels.get(i).setBoardSizeEasyMode(GameLevel.EASY_BOARD_SIZE+i);
            }
            if (GameLevel.INTERMEDIATE_BOARD_SIZE+i > GameLevel.MAX_BOARD_SIZE) {
                gameLevels.get(i).setBoardSizeIntermediateMode(GameLevel.MAX_BOARD_SIZE);
            } else {
                gameLevels.get(i).setBoardSizeIntermediateMode(GameLevel.INTERMEDIATE_BOARD_SIZE + i);
            }
            if (GameLevel.PRO_BOARD_SIZE+i > GameLevel.MAX_BOARD_SIZE){
                gameLevels.get(i).setBoardSizeProMode(GameLevel.MAX_BOARD_SIZE);
            } else {
                gameLevels.get(i).setBoardSizeProMode(GameLevel.PRO_BOARD_SIZE+i);
            }
        }
    }

    private void setLevelNumberOfBombs(){
        for (int i=1; i<NUMBER_OF_LEVELS; i++){
            gameLevels.get(i).setNumberOfBombsEasyMode((gameLevels.get(i).getBoardSizeEasyMode()*gameLevels.get(i).getBoardSizeEasyMode())/6);

            gameLevels.get(i).setNumberOfBombsIntermediateMode((gameLevels.get(i).getBoardSizeIntermediateMode()*gameLevels.get(i).getBoardSizeIntermediateMode())/6+i);

            if (i>6){
                gameLevels.get(i).setNumberOfBombsHardMode(GameLevel.PRO_NUMBER_OF_MINES+(6*6)+6*2 );
            } else {
                gameLevels.get(i).setNumberOfBombsHardMode(GameLevel.PRO_NUMBER_OF_MINES+(i*i)+i*2 );
            }
        }
    }
    public boolean allModesWon(GameLevel g) {
        if (g.getBestTimeProMode() == Integer.MAX_VALUE || g.getBestIntermediateMode() == Integer.MAX_VALUE || g.getBestTimeEasyMode() == Integer.MAX_VALUE){
            return false;
        }

        return true;
    }
    public void setCurrentGameLevel(String gameLevel){
        switch (gameLevel) {
            case "vitas":
                currentGameLevel = vitas;
                break;
            case "classic":
                currentGameLevel = classic;
                break;
            case "trump":
                currentGameLevel = trump;
                break;
            case "quagmire":
                currentGameLevel = quagmire;
                break;
            case "borat":
                currentGameLevel = borat;
                break;
            case "obama":
                currentGameLevel = obama;
                break;
            case "dalai lama":
                currentGameLevel = dalaiLama;
                break;
            case "oprah":
                currentGameLevel = oprah;
                break;
            case "timberlake":
                currentGameLevel = timberlake;
                break;
            case "einstein":
                currentGameLevel = einstein;
                break;
            case "gal":
                currentGameLevel = gal;
                break;
        }
    }
}

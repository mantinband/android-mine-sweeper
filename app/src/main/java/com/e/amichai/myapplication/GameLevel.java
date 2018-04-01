package com.e.amichai.myapplication;

import java.io.Serializable;

public class GameLevel implements Serializable {

    public static final int EASY_BOARD_SIZE = 5;
    public static final int INTERMEDIATE_BOARD_SIZE = 10;
    public static final int PRO_BOARD_SIZE = 15;
    public static final int MAX_BOARD_SIZE = 21;

    public static final int EASY_NUMBER_OF_MINES = 4;
    public static final int INTERMEDIATE_NUMBER_OF_MINES = 16;
    public static final int PRO_NUMBER_OF_MINES = 38;

    private String themeName;

    private int numberOfBombsEasyMode;
    private int numberOfBombsIntermediateMode;
    private int numberOfBombsHardMode;

    private int boardSizeEasyMode;
    private int boardSizeIntermediateMode;
    private int boardSizeProMode;

    private int bestTimeEasyMode;
    private int bestIntermediateMode;
    private int bestTimeProMode;

    private boolean levelLocked;

    private GameLevel nextLevel;
    private GameLevel previousLevel;

    public GameLevel(String themeName){
        this.themeName = new String(themeName);
        bestTimeEasyMode = Integer.MAX_VALUE;
        bestIntermediateMode = Integer.MAX_VALUE;
        bestTimeProMode = Integer.MAX_VALUE;
        levelLocked = true;
        boardSizeEasyMode = EASY_BOARD_SIZE;
        boardSizeIntermediateMode = INTERMEDIATE_BOARD_SIZE;
        boardSizeProMode = PRO_BOARD_SIZE;
        numberOfBombsEasyMode = EASY_NUMBER_OF_MINES;
        numberOfBombsIntermediateMode = INTERMEDIATE_NUMBER_OF_MINES;
        numberOfBombsHardMode = PRO_NUMBER_OF_MINES;

    }

    public GameLevel getNextLevel(){
        return nextLevel;
    }

    public GameLevel getPreviousLevel(){ return previousLevel;}

    public void setPreviousLevel(GameLevel previousLevel){ this.previousLevel = previousLevel;}

    public void setNextLevel(GameLevel nextLevel){
        this.nextLevel = nextLevel;
    }

    public void setLockedStatus(boolean status){
        levelLocked = status;
    }

    public boolean getLockedStatus(){
        return levelLocked;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    public int getNumberOfBombsEasyMode() {
        return numberOfBombsEasyMode;
    }

    public void setNumberOfBombsEasyMode(int numberOfBombsEasyMode) {
        this.numberOfBombsEasyMode = numberOfBombsEasyMode;
    }

    public int getNumberOfBombsIntermediateMode() {
        return numberOfBombsIntermediateMode;
    }

    public void setNumberOfBombsIntermediateMode(int numberOfBombsIntermediateMode) {
        this.numberOfBombsIntermediateMode = numberOfBombsIntermediateMode;
    }

    public int getNumberOfBombsHardMode() {
        return numberOfBombsHardMode;
    }

    public void setNumberOfBombsHardMode(int numberOfBombsHardMode) {
        this.numberOfBombsHardMode = numberOfBombsHardMode;
    }

    public int getBoardSizeEasyMode() {
        return boardSizeEasyMode;
    }

    public void setBoardSizeEasyMode(int boardSizeEasyMode) {
        this.boardSizeEasyMode = boardSizeEasyMode;
    }

    public int getBoardSizeIntermediateMode() {
        return boardSizeIntermediateMode;
    }

    public void setBoardSizeIntermediateMode(int boardSizeIntermediateMode) {
        this.boardSizeIntermediateMode = boardSizeIntermediateMode;
    }

    public int getBoardSizeProMode() {
        return boardSizeProMode;
    }

    public void setBoardSizeProMode(int boardSizeProMode) {
        this.boardSizeProMode = boardSizeProMode;
    }

    public int getBestTimeEasyMode() {
        return bestTimeEasyMode;
    }

    public void setBestTimeEasyMode(int bestTimeEasyMode) {
        this.bestTimeEasyMode = bestTimeEasyMode;
    }

    public int getBestIntermediateMode() {
        return bestIntermediateMode;
    }

    public void setBestIntermediateMode(int bestIntermediateMode) {
        this.bestIntermediateMode = bestIntermediateMode;
    }

    public int getBestTimeProMode() {
        return bestTimeProMode;
    }

    public void setBestTimeProMode(int bestTimeProMode) {
        this.bestTimeProMode = bestTimeProMode;
    }

    public boolean hasNext(){ return nextLevel!=null;}
}

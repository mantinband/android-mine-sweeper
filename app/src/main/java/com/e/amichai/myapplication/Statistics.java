package com.e.amichai.myapplication;


public class Statistics {
    private int numberOfProGamesPlayed;
    private int numberOfIntermediateGamesPlayed;
    private int numberOfBeginnerGamesPlayed;

    private int numberOfProGamesWon;
    private int numberOfIntermediateGamesWon;
    private int numberOfBeginnerGamesWon;

    public Statistics(){
    }


    public void setNumberOfBeginnerGamesPlayed(int numberOfBeginnerGamesPlayed){
        this.numberOfBeginnerGamesPlayed = numberOfBeginnerGamesPlayed;
    }
    public int getNumberOfGamesPlayed() {
        return numberOfBeginnerGamesPlayed+numberOfIntermediateGamesPlayed+numberOfProGamesPlayed;
    }

    public int getNumberOfProGamesPlayed() {
        return numberOfProGamesPlayed;
    }

    public void setNumberOfProGamesPlayed(int numberOfProGamesPlayed) {
        this.numberOfProGamesPlayed = numberOfProGamesPlayed;
    }

    public int getNumberOfIntermediateGamesPlayed() {
        return numberOfIntermediateGamesPlayed;
    }

    public void setNumberOfIntermediateGamesPlayed(int numberOfIntermediateGamesPlayed) {
        this.numberOfIntermediateGamesPlayed = numberOfIntermediateGamesPlayed;
    }

    public int getNumberOfBeginnerGamesPlayed() {
        return numberOfBeginnerGamesPlayed;
    }

    public int getNumberOfProGamesWon() {
        return numberOfProGamesWon;
    }

    public void setNumberOfProGamesWon(int numberOfProGamesWon) {
        this.numberOfProGamesWon = numberOfProGamesWon;
    }

    public int getNumberOfIntermediateGamesWon() {
        return numberOfIntermediateGamesWon;
    }

    public void setNumberOfIntermediateGamesWon(int numberOfIntermediateGamesWon) {
        this.numberOfIntermediateGamesWon = numberOfIntermediateGamesWon;
    }

    public int getNumberOfBeginnerGamesWon() {
        return numberOfBeginnerGamesWon;
    }

    public void setNumberOfBeginnerGamesWon(int numberOfBeginnerGamesWon) {
        this.numberOfBeginnerGamesWon = numberOfBeginnerGamesWon;
    }

    public float getWinningPercentageBeginnerMode(){
        if (numberOfBeginnerGamesPlayed == 0){
            return 0;
        }
        return 100*((float)numberOfBeginnerGamesWon)/numberOfBeginnerGamesPlayed;
    }
    public float getWinningPercentageIntermediateMode(){
        if (numberOfIntermediateGamesPlayed == 0){
            return 0;
        }
        return 100*((float)numberOfIntermediateGamesWon)/numberOfIntermediateGamesPlayed;
    }
    public float getWinningPercentageProMode(){
        if (numberOfProGamesPlayed == 0){
            return 0;
        }
        return 100*((float)numberOfProGamesWon)/numberOfProGamesPlayed;
    }

    public float getTotalWinningPercentage(){
        if (getNumberOfGamesPlayed() == 0){
            return 0;
        }
        return 100*((float)getNumberOfGamesWon()/getNumberOfGamesPlayed());
    }

    public int getNumberOfGamesWon() {
        return numberOfBeginnerGamesWon + numberOfProGamesWon + numberOfIntermediateGamesWon;
    }

    public void resetData() {
        numberOfProGamesPlayed = 0;
        numberOfIntermediateGamesPlayed = 0;
        numberOfBeginnerGamesPlayed = 0;

        numberOfProGamesWon = 0;
        numberOfIntermediateGamesWon = 0;
        numberOfBeginnerGamesWon = 0;
    }
}

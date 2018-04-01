package com.e.amichai.myapplication;


public class Spot {
    private boolean isBomb;
    private int numberOfNeighborBombs;
    private boolean isVisible;
    private boolean isFlagged;
    private boolean isQuestionMarked;

    public Spot() {
        isBomb = false;
        numberOfNeighborBombs = 0;
        isVisible = false;
        isFlagged = false;
        isQuestionMarked = false;
    }

    public void setQuestionMarked(boolean status){
        isQuestionMarked = status;
    }

    public boolean getQuestionMarkedStatus(){
        return isQuestionMarked;
    }
    public void setBombStatus(boolean status) {
        isBomb = status;
    }

    public void setNumberOfNeighborBombs(int numberOfNeighborBombs) {
        this.numberOfNeighborBombs = numberOfNeighborBombs;
    }

    public void setVisibility(boolean status) {
        isVisible = status;
    }

    public boolean getBombStatus() {
        return isBomb;
    }

    public int getNumberOfNeighborBombs() {
        return numberOfNeighborBombs;
    }

    public boolean getVisibilityStatus() {
        return isVisible;
    }

    public void setFlagged(boolean status){
        isFlagged = status;
    }

    public boolean getFlaggedStatus(){
        return isFlagged;
    }
}


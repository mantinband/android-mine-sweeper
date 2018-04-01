package com.e.amichai.myapplication;

import android.widget.ImageView;

public class Animations {
    private int arrowFrame;

    private boolean startGameAnimationOn;

    private static final int NUMBER_OF_FRAMES_ARROW_ANIMATION = 10;
    private static final int NUMBER_OF_FRAMES_TIME_ANIMATION = 12;

    public Animations(){
        arrowFrame = 1;
    }


    public void arrow(ImageView arrowAnimationImageView){
        if (arrowFrame == NUMBER_OF_FRAMES_ARROW_ANIMATION+1){
            arrowFrame = 1;
        }
        switch (arrowFrame++) {
            case 1:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a1); break;
            case 2:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a2); break;
            case 3:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a3); break;
            case 4:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a4); break;
            case 5:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a5); break;
            case 6:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a6); break;
            case 7:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a7); break;
            case 8:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a8); break;
            case 9:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a9); break;
            case 10:
                arrowAnimationImageView.setBackgroundResource(R.drawable.a10); break;

            }
    }
}

package com.e.amichai.myapplication;

import android.widget.Button;
import android.widget.ImageView;

public class Theme {
    private String themeName;
    public static String gameStyle = "classic";

    public static final String DEFAULT_THEME = "classic";

    public Theme(String themeName){
        this.themeName = themeName;
    }

    public Theme() {
        themeName = new String(DEFAULT_THEME);
    }

    public String getThemeName(){
        return themeName;
    }

    public void setThemeName(String themeName){
        this.themeName = themeName;
    }

    public void smileyGameImage(Button b){
        switch (themeName) {
            case "classic":
                b.setBackgroundResource(R.drawable.game); break;
            case "vitas":
            case "vitas_dark":
                b.setBackgroundResource(R.drawable.game_vitas); break;
            case "trump":
            case "trump_dark":
                b.setBackgroundResource(R.drawable.game_trump); break;
            case "quagmire":
            case "quagmire_dark":
                b.setBackgroundResource(R.drawable.game_quagmire); break;
            case "borat":
            case "borat_dark":
                b.setBackgroundResource(R.drawable.game_borat); break;
            case "obama":
            case "obama_dark":
                b.setBackgroundResource(R.drawable.game_obama); break;
            case "dalai lama":
            case "dalai lama dark":
                b.setBackgroundResource(R.drawable.game_dalai_lama); break;
            case "oprah":
            case "oprah_dark":
                b.setBackgroundResource(R.drawable.oprah_game); break;
            case "timberlake":
            case "timberlake_dark":
                b.setBackgroundResource(R.drawable.timberlake_game); break;
            case "einstein":
            case "einstein_dark":
                b.setBackgroundResource(R.drawable.game_einstein); break;
            case "gal":
            case "gal_dark":
                b.setBackgroundResource(R.drawable.game_gal); break;
        }
    }
    public void smileyPressedImage(Button b){
        switch (themeName) {
            case "classic":
                b.setBackgroundResource(R.drawable.pressed); break;
            case "vitas":
            case "vitas_dark":
                b.setBackgroundResource(R.drawable.pressed_vitas); break;
            case "trump":
            case "trump_dark":
                b.setBackgroundResource(R.drawable.pressed_trump); break;
            case "quagmire":
            case "quagmire_dark":
                b.setBackgroundResource(R.drawable.pressed_quagmire); break;
            case "borat":
            case "borat_dark":
                b.setBackgroundResource(R.drawable.pressed_borat); break;
            case "obama":
            case "obama_dark":
                b.setBackgroundResource(R.drawable.pressed_obama); break;
            case "dalai lama":
            case "dalai lama dark":
                b.setBackgroundResource(R.drawable.pressed_dalai_lama); break;
            case "oprah":
            case "oprah_dark":
                b.setBackgroundResource(R.drawable.pressed_oprah); break;
            case "timberlake":
            case "timberlake_dark":
                b.setBackgroundResource(R.drawable.timberlake_pressed); break;
            case "einstein":
            case "einstein_dark":
                b.setBackgroundResource(R.drawable.pressed_einstein); break;
            case "gal":
            case "gal_dark":
                b.setBackgroundResource(R.drawable.pressed_gal); break;
        }
    }

    public void smileyWon(Button b){
        switch (themeName) {
            case "classic":
                b.setBackgroundResource(R.drawable.win); break;
            case "vitas":
            case "vitas_dark":
            b.setBackgroundResource(R.drawable.win_vitas); break;
            case "trump":
            case "trump_dark":
                b.setBackgroundResource(R.drawable.win_trump); break;
            case "quagmire":
            case "quagmire_dark":
                b.setBackgroundResource(R.drawable.win_quagmire); break;
            case "borat":
            case "borat_dark":
                b.setBackgroundResource(R.drawable.win_borat); break;
            case "obama":
            case "obama_dark":
                b.setBackgroundResource(R.drawable.win_obama); break;
            case "dalai lama":
            case "dalai lama_dark":
                b.setBackgroundResource(R.drawable.win_dalai_lama); break;
            case "oprah":
            case "oprah_dark":
                b.setBackgroundResource(R.drawable.win_oprah); break;
            case "timberlake":
            case "timberlake_dark":
                b.setBackgroundResource(R.drawable.timberlake_win); break;
            case "einstein":
            case "einstein_dark":
                b.setBackgroundResource(R.drawable.win_einstein); break;
            case "gal":
            case "gal_dark":
                b.setBackgroundResource(R.drawable.gal_win); break;
        }
    }


    public void smileyLost(Button b){
        switch (themeName) {
            case "classic":
                b.setBackgroundResource(R.drawable.lose); break;
            case "vitas":
            case "vitas_dark":
            b.setBackgroundResource(R.drawable.lose_vitas); break;
            case "trump":
            case "trump_dark":
                b.setBackgroundResource(R.drawable.lose_trump); break;
            case "quagmire":
            case "quagmire_dark":
                b.setBackgroundResource(R.drawable.lose_quagmire); break;
            case "borat":
            case "borat_dark":
                b.setBackgroundResource(R.drawable.lose_borat); break;
            case "obama":
            case "obama_dark":
                b.setBackgroundResource(R.drawable.lose_obama); break;
            case "dalai lama":
            case "dalai lama_dark":
                b.setBackgroundResource(R.drawable.lose_dalai_lama); break;
            case "oprah":
            case "oprah_dark":
                b.setBackgroundResource(R.drawable.lose_oprah); break;
            case "timberlake":
            case "timberlake_dark":
                b.setBackgroundResource(R.drawable.timberlake_lose); break;
            case "einstein":
            case "einstein_dark":
                b.setBackgroundResource(R.drawable.lose_einstein); break;
            case "gal":
            case "gal_dark":
                b.setBackgroundResource(R.drawable.gal_lose); break;
        }
    }


    public void setThemeButton(Button b) {
        switch (GameTheme.currentGameLevel.getThemeName()) {
            case "classic":
                b.setBackgroundResource(R.drawable.smiley); break;
            case "vitas":
                b.setBackgroundResource(R.drawable.vitas); break;
            case "vitas_dark":
                b.setBackgroundResource(R.drawable.vitas_dark); break;
            case "trump":
                b.setBackgroundResource(R.drawable.trump); break;
            case "trump_dark":
            b.setBackgroundResource(R.drawable.trump_dark); break;
            case "quagmire":
                b.setBackgroundResource(R.drawable.quagmire); break;
            case "quagmire_dark":
            b.setBackgroundResource(R.drawable.quagmire_dark); break;
            case "borat":
                b.setBackgroundResource(R.drawable.borat); break;
            case "borat_dark":
            b.setBackgroundResource(R.drawable.borat_dark); break;
            case "obama":
                b.setBackgroundResource(R.drawable.obama); break;
            case "obama_dark":
            b.setBackgroundResource(R.drawable.obama_dark); break;
            case "dalai lama":
                b.setBackgroundResource(R.drawable.dalai_lama); break;
            case "dalai lama dark":
            b.setBackgroundResource(R.drawable.dalai_lama_dark); break;
            case "oprah":
                b.setBackgroundResource(R.drawable.oprah_winfrey); break;
            case "oprah_dark":
            b.setBackgroundResource(R.drawable.oprah_dark); break;
            case "timberlake":
                b.setBackgroundResource(R.drawable.timberlake); break;
            case "timberlake_dark":
            b.setBackgroundResource(R.drawable.timberlake_dark); break;
            case "einstein":
                b.setBackgroundResource(R.drawable.einstein); break;
            case "einstein_dark":
            b.setBackgroundResource(R.drawable.einstein_dark); break;
            case "gal":
                b.setBackgroundResource(R.drawable.gal); break;
            case "gal_dark":
            b.setBackgroundResource(R.drawable.gal_dark); break;
        }
    }

    public static void setUnclickedButtonImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_square);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_square);
            } else {
                b.setBackgroundResource(R.drawable.square);
            }
        } else {
            b.setBackgroundResource(R.drawable.square_dark);
        }
    }

    public static void setFlagButtonImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_flag);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_flag);
            } else {
                b.setBackgroundResource(R.drawable.flag);
            }
        } else {
            b.setBackgroundResource(R.drawable.flag_dark);
        }
    }
    public static void setFlagButtonImage(ImageView b){
        b.setImageResource(R.drawable.medium_flag);

    }
    public static void setClickedFlagButtonImage(ImageView b){
        b.setImageResource(R.drawable.medium_flag_pressed);

    }
    public static void setClickedFlagButtonImage(Button b){
        if (MainActivity.gameMode.equals("pro")){
            b.setBackgroundResource(R.drawable.pro_flag_pressed);
        } else if (MainActivity.gameMode.equals("medium") ){
            b.setBackgroundResource(R.drawable.medium_flag_pressed);
        } else {
            b.setBackgroundResource(R.drawable.flag_pressed);
        }
    }

    public static void setQuestionmarkButtonImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_questionmark);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_questionmark);
            } else {
                b.setBackgroundResource(R.drawable.questionmark);
            }
        } else {
            b.setBackgroundResource(R.drawable.questionmark_dark);
        }
    }

    public static void setNumberOneImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_one);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_one);
            } else {
                b.setBackgroundResource(R.drawable.one);
            }
        } else {
            b.setBackgroundResource(R.drawable.one_dark);
        }
    }
    public static void setNumberTwoImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_two);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_two);
            } else {
                b.setBackgroundResource(R.drawable.two);
            }
        } else {
            b.setBackgroundResource(R.drawable.two_dark);
        }
    }
    public static void setNumberThreeImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_three);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_three);
            } else {
                b.setBackgroundResource(R.drawable.three);
            }
        } else {
            b.setBackgroundResource(R.drawable.three_dark);
        }
    }

    public static void setNumberFourImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_four);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_four);
            } else {
                b.setBackgroundResource(R.drawable.four);
            }
        } else {
            b.setBackgroundResource(R.drawable.four_dark);
        }
    }
    public static void setNumberFiveImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_five);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_five);
            } else {
                b.setBackgroundResource(R.drawable.five);
            }
        } else {
            b.setBackgroundResource(R.drawable.five_dark);
        }
    }
    public static void setNumberSixImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_six);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_six);
            } else {
                b.setBackgroundResource(R.drawable.six);
            }
        } else {
            b.setBackgroundResource(R.drawable.six_dark);
        }
    }

    public static void setNumberSevenImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_seven);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_seven);
            } else {
                b.setBackgroundResource(R.drawable.seven);
            }
        } else {
            b.setBackgroundResource(R.drawable.seven_dark);
        }
    }

    public static void setNumberEightImage(Button b){
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_eight);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_eight);
            } else {
                b.setBackgroundResource(R.drawable.eight);
            }
        } else {
            b.setBackgroundResource(R.drawable.eight_dark);
        }
    }

    public static void setNumberZeroImage(Button b) {
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_zero);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_zero);
            } else {
                b.setBackgroundResource(R.drawable.zero);
            }
        } else {
            b.setBackgroundResource(R.drawable.zero_dark);
        }
    }

    public static void setBombClickedImage(Button b) {
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_bomklicked);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_bomklicked);
            } else {
                b.setBackgroundResource(R.drawable.bomkclicked);
            }
        } else {
            b.setBackgroundResource(R.drawable.bomkclicked_dark);
        }
    }

    public static void setMineImage(Button b) {
        if (gameStyle.equals("classic")) {
            if (MainActivity.gameMode.equals("pro")) {
                b.setBackgroundResource(R.drawable.pro_mine);
            } else if (MainActivity.gameMode.equals("medium")) {
                b.setBackgroundResource(R.drawable.medium_mine);
            } else {
                b.setBackgroundResource(R.drawable.mine);
            }
        } else {
            b.setBackgroundResource(R.drawable.mine_dark);
        }
    }
}

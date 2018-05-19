package com.e.amichai.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Opener extends AppCompatActivity {
    private Handler uiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opener);

        uiHandler = new Handler(); // anything posted to this handler will run on the UI Thread
        final Runnable onUi = new Runnable() {
            @Override
            public void run() {
                Intent game = new Intent (Opener.this, MainActivity.class);
                startActivity (game);
                overridePendingTransition( R.anim.fadein, R.anim.fadeout);
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                            finish();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
            }
        };

        Runnable background = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep ( 1500);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }

                uiHandler.post( onUi );
            }
        };

        new Thread( background ).start();
    }
}

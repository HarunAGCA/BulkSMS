package com.example.harun.bulksms.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.harun.bulksms.R;

public class SplashScreenActivity extends AppCompatActivity {

    private int SLEEP_TIME = 0;// millisecond

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        IntentLaucher intentLaucher = new IntentLaucher();
        intentLaucher.start();

    }


    private class IntentLaucher extends Thread {

        @Override
        public void run() {

            try {

                Thread.sleep(SLEEP_TIME);

            } catch (InterruptedException e) {

            } finally {

                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();

            }
        }


    }
}

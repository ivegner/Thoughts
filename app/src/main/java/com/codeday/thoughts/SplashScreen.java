package com.codeday.thoughts;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;

public class SplashScreen extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        context = this;

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    // Enable Local Datastore.
                    Parse.enableLocalDatastore(context);

                    Parse.initialize(context, "amWtu0dCB9xx0XJ8a4dQA4cy7XayDikxCGpdhkVb", "0XbZemPscEidvvl1NE0wrCKXQayuS48Fr7I6XF4O");

                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, PeruseActivity.class);
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

}

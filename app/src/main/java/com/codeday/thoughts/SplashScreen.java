package com.codeday.thoughts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.appspot.thoughtsapp_1141.thoughts.Thoughts;
import com.appspot.thoughtsapp_1141.thoughts.model.ThoughtsIOThought;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

public class SplashScreen extends AppCompatActivity {

    private Context context;
    private Thoughts service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash_screen);
        context = this;

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    //Google Mobile Endpoints
                    Thoughts.Builder builder = new Thoughts.Builder(
                            AndroidHttp.newCompatibleTransport(),new AndroidJsonFactory(), null
                    );
                    service = builder.build();

                    try {
                        ThoughtsIOThought thought = service.getThought().execute();
                        Log.d("REC THO", "new thought: "+thought.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(SplashScreen.this, ReadActivity.class);
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

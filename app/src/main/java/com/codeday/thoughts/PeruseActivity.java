package com.codeday.thoughts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class PeruseActivity extends AppCompatActivity {

    private TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peruse);

        textViewMessage = (TextView) findViewById(R.id.viewTextMessage);
        findViewById(R.id.messageContainer).setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                textViewMessage.setText("hey");
            }
        });

    }

    public void startPostActivity(View v){
        Intent intent = new Intent(this,PostActivity.class);
        startActivity(intent);
    }
}

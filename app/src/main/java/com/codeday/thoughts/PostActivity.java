package com.codeday.thoughts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    private int inputLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_post);

        inputLength = 600;
        TextView charRemain = (TextView) findViewById(R.id.charRemainView);
        charRemain.setText(inputLength + " characters remaining.");
    }

    public void post(View view) {

    }

    private boolean isValidInput(String str) {
        if (str.isEmpty()) { return false; }

        else if (str.length() < 600) { return false;}

        else { return true; }
    }
}

package com.codeday.thoughts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PeruseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peruse);
    }

    public void startPostActivity(View v){
        Intent intent = new Intent(this,PostActivity.class);
        startActivity(intent);
    }
}

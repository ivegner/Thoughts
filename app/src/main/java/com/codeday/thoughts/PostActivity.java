package com.codeday.thoughts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    private int inputLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_post);

        // Make sure the scrollbar isn't visible when scrolling
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);

        sv.setVerticalScrollBarEnabled(false);
        sv.setHorizontalScrollBarEnabled(false);

        inputLength = 600;
        TextView charRemain = (TextView) findViewById(R.id.charRemainView);
        charRemain.setText(inputLength + " characters remaining.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_post; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), PeruseActivity.class);
        startActivityForResult(myIntent, 0);
        return true;

    }

    public void post(View view) {

    }

    private boolean isValidInput(String str) {
        if (str.isEmpty()) { return false; }

        else if (str.length() < 600) { return false;}

        else { return true; }
    }
}

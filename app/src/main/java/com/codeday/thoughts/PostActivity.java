package com.codeday.thoughts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

public class PostActivity extends AppCompatActivity {

    private int inputLength;
    private TextView inputView;
    private TextView charRemainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_post);

        // Make sure the scrollbar isn't visible when scrolling
        ScrollView sv = (ScrollView)findViewById(R.id.scrollView);

        sv.setVerticalScrollBarEnabled(false);
        sv.setHorizontalScrollBarEnabled(false);

        inputView = (TextView) findViewById(R.id.inputText);

        inputLength = 600;
        charRemainView = (TextView) findViewById(R.id.charRemainView);
        inputLength = 600 - inputView.getText().toString().length();
        charRemainView.setText(inputLength + " characters remaining.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_post; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.sendView:
                post();
                return true;
        }

        Intent myIntent = new Intent(getApplicationContext(), PeruseActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    public void post() {
        if(isValidInput(inputView.getText().toString())) {
            ParseObject parseObject = new ParseObject("Content");
            parseObject.put("Text", inputView.getText().toString());
            parseObject.saveInBackground();

            Intent myIntent = new Intent(getApplicationContext(), PeruseActivity.class);
            Toast.makeText(getApplicationContext(), "Your thought has been posted.", Toast.LENGTH_LONG).show();
            startActivity(myIntent);
        }
    }

    private boolean isValidInput(String str) {
        if (str.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a thought!",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (str.length() > 600) {
            Toast.makeText(getApplicationContext(), "Please limit your thought to 600 characters.",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else if (str.length() < 10) {
            Toast.makeText(getApplicationContext(), "Please enter more than 10 characters.",
                    Toast.LENGTH_SHORT).show();
            return false;
        } else { return true; }
    }
}

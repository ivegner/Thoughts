package com.codeday.thoughts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    private boolean isParseInitialized = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        if(!isParseInitialized) {
            Parse.initialize(this, "amWtu0dCB9xx0XJ8a4dQA4cy7XayDikxCGpdhkVb", "0XbZemPscEidvvl1NE0wrCKXQayuS48Fr7I6XF4O");
            isParseInitialized = true;
        }
    }
}

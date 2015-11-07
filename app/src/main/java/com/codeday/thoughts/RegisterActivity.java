package com.codeday.thoughts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.parse.ParseUser;

public class RegisterActivity extends AppCompatActivity {

    ParseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        user = new ParseUser();
    }
}

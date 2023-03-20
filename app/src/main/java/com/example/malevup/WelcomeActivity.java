package com.example.malevup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    final static String userVariableKey = "USER_VARIABLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void nextRegister(View v)
    {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void nextLogin(View v)
    {
        startActivity(new Intent(this, LoginActivity.class));
    }
}